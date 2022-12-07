package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.common.OpenIdHolder;
import cn.edu.szu.Bajie.constant.CacheConstant;
import cn.edu.szu.Bajie.constant.CacheTimeInterval;
import cn.edu.szu.Bajie.converter.CanteenConverter;
import cn.edu.szu.Bajie.converter.DishConverter;
import cn.edu.szu.Bajie.converter.WindowsConverter;
import cn.edu.szu.Bajie.dto.add.CollectionAddDto;
import cn.edu.szu.Bajie.dto.query.CollectionsQueryDto;
import cn.edu.szu.Bajie.dto.result.CollectCanteenResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.CollectWindowResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.entity.*;
import cn.edu.szu.Bajie.service.*;
import cn.edu.szu.Bajie.util.CacheService;
import cn.edu.szu.Bajie.util.DistanceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.mapper.CollectionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【collection】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@AllArgsConstructor
@Slf4j
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

    private CanteenService canteenService;

    private CanteenDynamicService canteenDynamicService;

    private WindowsService windowsService;

    private WindowsConverter windowsConverter;

    private DishService dishService;

    private DishDynamicService dishDynamicService;

    private CanteenConverter canteenConverter;

    private DishConverter dishConverter;


    private CacheService cacheService;


    @Override
    public List<Collection> getUserCollections(String userId) {

        return cacheService.getByCacheList(
                // redisKey
                MessageFormat.format(CacheConstant.COLLECTION_LIST,userId),
                // 获取用户收藏列表
                ()->this.list(
                        new LambdaQueryWrapper<Collection>()
                                .eq(Collection::getOpenId,userId)
                                .eq(Collection::getIsCollected,1)
                ),
                // 字节码
                Collection.class,
                CacheTimeInterval.AN_HOUR
        );
    }

    @Override
    public void doCollect(CollectionAddDto dto) {

        Object flag =null;

        switch (dto.getType()){
            case 0:
                flag = canteenService.getBaseCanteenById(dto.getTargetId());break;
            case 1:
                flag = windowsService.getWindowInfo(dto.getTargetId());break;
            case 2:
                flag = dishService.getBaseDishInfo(dto.getTargetId());break;
            default:
        }

        if(ObjectUtil.isNull(flag)){
            throw new IllegalArgumentException("收藏id不存在");
        }

        // 获取用户id
        String userId = OpenIdHolder.openIdThreadLocal.get();

        List<Collection> userCollections = getUserCollections(userId);

        Collection newCollection = null;

        for (Collection collection : userCollections) {

            if(collection.getCollectType().equals(dto.getType()) && collection.getTargetId().equals(dto.getTargetId())){
                // 更新收藏状态
                collection.setIsCollected(ObjectUtil.isNull(dto.getIsCollected())?collection.getIsCollected()^1:dto.getIsCollected());

                newCollection =collection;
                break;
            }
        }

        if(ObjectUtil.isNull(newCollection)){
            newCollection =Collection
                    .builder()
                    .collectType(dto.getType())
                    .isCollected(1)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .openId(userId)
                    .targetId(dto.getTargetId())
                    .build();

            userCollections.add(newCollection);
        }

        // 添加到消息队列更新数据库
        cacheService.lPushObj(CacheConstant.COLLECTION_MQ,newCollection);

        // 更新缓存
        cacheService.setList(
                // redisKey
                MessageFormat.format(CacheConstant.COLLECTION_LIST,userId),
                userCollections,
                CacheTimeInterval.AN_HOUR
        );

    }

    /**
     * 每5分钟清空消息队列
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void updateToDatabase(){

        log.info("开始清理消息队列！！！");

        cacheService
                .range(CacheConstant.COLLECTION_MQ, 0, -1, Collection.class)
                .forEach(collection -> {

                    LambdaQueryWrapper<Collection> wrapper = new LambdaQueryWrapper<Collection>()
                            .eq(Collection::getCollectType, collection.getCollectType())
                            .eq(Collection::getTargetId, collection.getTargetId());


                    long count = this.count(
                            wrapper
                    );

                    if(count > 0){
                        this.update(
                                collection,
                                wrapper
                        );
                        return;
                    }

                    this.save(collection);

                });

        cacheService.delete(CacheConstant.COLLECTION_MQ);
    }

    @Override
    public List<Object> getCollectionInfos(CollectionsQueryDto dto) {

        // 构造排序的比较器
        Comparator<Collection.ICollectionHelper> comparing = null;
        // 根据参数选择排序的比较器
        switch (dto.getSortType()){
            case 0:
                // 时间倒叙
                comparing = Comparator.comparing(Collection.ICollectionHelper::getTimeStamp).reversed();
                break;
            case 1:
                // 时间升序
                comparing = Comparator.comparing(Collection.ICollectionHelper::getTimeStamp);
                break;
            case 2:
                // 名字倒叙
                comparing = Comparator.comparing(Collection.ICollectionHelper::getName);
                break;
            case 3:
                // 名字升序
                comparing = Comparator.comparing(Collection.ICollectionHelper::getName).reversed();
                break;
            case 4:
                // 距离升序
                comparing = Comparator.comparing(Collection.ICollectionHelper::getDistance);
                break;
            default:
                // 距离倒叙
                comparing = Comparator.comparing(Collection.ICollectionHelper::getDistance).reversed();
        }

        // 定义获取收藏信息中的餐厅信息的逻辑
        Function<Collection,Collection.ICollectionHelper> collectCanteenFunc = (collection) -> {
            // 获取餐厅信息
            Canteen canteen = canteenService.getBaseCanteenById(collection.getTargetId());

            if(ObjectUtil.isNull(canteen)){
                return null;
            }

            // 将餐厅属性值赋值到结果类中
            CollectCanteenResultDto collectCanteen = canteenConverter.canteen2CollectCanteen(canteen);

            // 获取餐厅动态信息
            CanteenDynamic canteenDynamic = canteenDynamicService.getById(canteen.getCanteenId());

            canteenConverter.canteenDynamic2CollectCanteen(canteenDynamic,collectCanteen);

            collectCanteen.setCollectedTime(collection.getUpdateTime());

            collectCanteen.setDistance(
                    // 计算距离
                    DistanceUtil.getDistance(
                            dto.getLongitude(),
                            dto.getLongitude(),
                            canteen.getCanteenPositionLongitude().doubleValue(),
                            canteen.getCanteenPositionLatitude().doubleValue()
                    )
            );

            collectCanteen.setCollectedType(collection.getCollectType());

            return collectCanteen;

        };

        // 获取收藏窗口信息
        Function<Collection,Collection.ICollectionHelper> collectWindowFunc = collection -> {

            // 获取窗口信息
            Windows windows = windowsService.getWindowInfo(collection.getTargetId());

            if(ObjectUtil.isNull(windows)){
                return null;
            }

            // 餐厅信息
            Canteen canteen = canteenService.getBaseCanteenById(windows.getCanteenId());

            // 菜品信息
            List<Dish> dishes = dishService.getBaseDishesByWinId(windows.getWinId());

            // 将数据赋值到结果类
            CollectWindowResultDto collectWindow = canteenConverter.canteen2collectWindow(canteen);

            windowsConverter.window2CollectWindow(windows,collectWindow);

            collectWindow.setCollectedTime(collection.getUpdateTime());

            collectWindow.setDistance(
                    // 计算距离
                    DistanceUtil.getDistance(
                            dto.getLongitude(),
                            dto.getLongitude(),
                            canteen.getCanteenPositionLongitude().doubleValue(),
                            canteen.getCanteenPositionLatitude().doubleValue()
                    )
            );

            collectWindow.setDishes(dishes);

            collectWindow.setCollectedType(collection.getCollectType());

            return collectWindow;
        };

        // 收藏菜品信息
        Function<Collection,Collection.ICollectionHelper> collectDishFunc = collection -> {

            // 菜品信息
            DishDetailResultDto dish = dishService.getDishDetail(collection.getTargetId());
            if(ObjectUtil.isNull(dish)){
                return null;
            }
            // 窗口信息
            Windows windows = windowsService.getWindowInfo(dish.getWinId());
            // 餐厅信息
            Canteen canteen = canteenService.getBaseCanteenById(windows.getCanteenId());
            // 将结果复制给结果类
            CollectDishResultDto collectDish = dishConverter.dishdetail2ColletDish(dish);

            canteenConverter.canteen2collectDish(canteen,collectDish);

            windowsConverter.window2CollectDish(windows,collectDish);

            collectDish.setCollectedTime(collection.getUpdateTime());

            collectDish.setDistance(
                    // 计算距离
                    DistanceUtil.getDistance(
                            dto.getLongitude(),
                            dto.getLongitude(),
                            canteen.getCanteenPositionLongitude().doubleValue(),
                            canteen.getCanteenPositionLatitude().doubleValue()
                    )
            );

            collectDish.setCollectedType(collection.getCollectType());

            return collectDish;

        };

        // 获取收藏信息列表
        return this.getUserCollections(OpenIdHolder.openIdThreadLocal.get())
                .stream()
                // 根据类型分组，转为map存储
                .collect(Collectors.groupingBy(Collection::getCollectType, Collectors.toList()))
                .entrySet()
                // 遍历每一个组的收藏集合
                .stream()
                .map((item) -> {

                    Function<Collection,Collection.ICollectionHelper> collectFunc = null;
                    // 根据收藏类型选择需要获取收藏信息
                    switch (item.getKey()){
                        case 0:
                            collectFunc = collectCanteenFunc;break;
                        case 1:
                            collectFunc = collectWindowFunc;break;
                        default:
                            collectFunc = collectDishFunc;
                    }
                    //
                    return item.getValue()
                            .stream()
                            .map(collectFunc)
                            .collect(Collectors.toList());
                })
                // 到这里结果是List<List<XXX>> 类型，下面将多个list变为一个list
                .reduce(
                        new LinkedList<>(),
                        (res,listItem)->{
                            res.addAll(listItem);
                            return res;
                        }
                )
                .stream()
                // 筛选符合距离的
                .filter(item-> dto.getDistance() <= 0 || item.getDistance() <= dto.getDistance())
                // 排序
                .sorted(
                        comparing
                )
                .collect(Collectors.toList());
    }


    @Override
    public Integer getCollectStatus(Integer type, Long targetId) {

        String userId = OpenIdHolder.openIdThreadLocal.get();

        return getUserCollections(userId)
                .stream()
                .anyMatch(collection -> collection.getCollectType().equals(type)&&
                        collection.getTargetId().equals(targetId)&&
                        collection.getIsCollected() == 1)?1:0;

    }


}




