package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.converter.CanteenConverter;
import cn.edu.szu.Bajie.converter.DishConverter;
import cn.edu.szu.Bajie.dto.query.CollectionsQueryDto;
import cn.edu.szu.Bajie.dto.result.CollectCanteenResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.CollectWindowResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.Windows;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.service.WindowsService;
import cn.edu.szu.Bajie.util.DistanceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.service.CollectionService;
import cn.edu.szu.Bajie.mapper.CollectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【collection】的数据库操作Service实现
* @createDate 2022-11-16 14:18:14
*/
@Service
@AllArgsConstructor
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

    private CanteenService canteenService;

    private WindowsService windowsService;

    private DishService dishService;

    private CanteenConverter canteenConverter;

    private DishConverter dishConverter;


    @Override
    public List<Object> getCollections(CollectionsQueryDto dto,String userId) {

        // 构造查询收藏列表条件
        LambdaQueryWrapper<Collection> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Collection::getCollectType,dto.getTypes())
                .eq(Collection::getIsCollected,1)
                .eq(Collection::getOpenId,userId);

        // 构造排序的比较器
        Comparator<Collection.ICollectionHelper> comparing = null;
        switch (dto.getSortType()){
            case 0:
                comparing = Comparator.comparing(Collection.ICollectionHelper::getTimeStamp).reversed();
                break;
            case 1:
                comparing = Comparator.comparing(Collection.ICollectionHelper::getTimeStamp);
                break;
            case 2:
                comparing = Comparator.comparing(Collection.ICollectionHelper::getName);
                break;
            case 3:
                comparing = Comparator.comparing(Collection.ICollectionHelper::getName).reversed();
                break;
            case 4:
                comparing = Comparator.comparing(Collection.ICollectionHelper::getDistance);
                break;
            default:
                comparing = Comparator.comparing(Collection.ICollectionHelper::getDistance).reversed();
        }

        // 获取餐厅信息的func
        Function<Collection,Collection.ICollectionHelper> collectCanteenFunc = (collection) -> {
            // 获取餐厅信息
            Canteen canteen = canteenService
                    .getOne(
                            new LambdaQueryWrapper<Canteen>()
                                    .eq(Canteen::getCanteenId, collection.getTargetId())
                                    .gt(Canteen::getIsOpening,dto.getStatus()-1)
                    );

            // 将餐厅属性值赋值到结果类中
            CollectCanteenResultDto collectCanteen = canteenConverter.canteen2collectCanteen(canteen);

            collectCanteen.setCollectedTime(collection.getUpdateTime());

            collectCanteen.setDistance(
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

        // 获取收藏信息func
        Function<Collection,Collection.ICollectionHelper> collectWindowFunc = collection -> {

            // 获取窗口信息
            Windows windows = windowsService.getOne(
                    new LambdaQueryWrapper<Windows>()
                            .eq(Windows::getWinId, collection.getTargetId())

            );
            // 餐厅信息
            Canteen canteen = canteenService.getOne(
                    new LambdaQueryWrapper<Canteen>()
                            .eq(Canteen::getCanteenId, windows.getCanteenId())
            );
            // 菜品信息
            List<Dish> dishes = dishService.list(
                    new LambdaQueryWrapper<Dish>()
                            .eq(Dish::getWinId, windows.getWinId())
            );

            // 将数据赋值到结果类
            CollectWindowResultDto collectWindow = canteenConverter.canteen2collectWindow(canteen);

            collectWindow.setWinId(windows.getWinId());
            collectWindow.setWinName(windows.getWinName());
            collectWindow.setIsOpening(windows.getIsOpening());

            collectWindow.setCollectedTime(collection.getUpdateTime());
            collectWindow.setDistance(
                    DistanceUtil.getDistance(
                            dto.getLongitude(),
                            dto.getLongitude(),
                            canteen.getCanteenPositionLongitude().doubleValue(),
                            canteen.getCanteenPositionLatitude().doubleValue()
                    )
            );

            collectWindow.setDishs(dishes);

            collectWindow.setCollectedType(collection.getCollectType());

            return collectWindow;
        };

        // 收藏菜品信息
        Function<Collection,Collection.ICollectionHelper> collectDishFunc = collection -> {

            // 菜品信息
            Dish dish = dishService.getOne(
                    new LambdaQueryWrapper<Dish>()
                            .eq(Dish::getDishId, collection.getTargetId())
            );
            // 窗口信息
            Windows windows = windowsService.getOne(
                    new LambdaQueryWrapper<Windows>()
                            .eq(Windows::getWinId, dish.getWinId())
            );
            // 餐厅信息
            Canteen canteen = canteenService.getOne(
                    new LambdaQueryWrapper<Canteen>()
                            .eq(Canteen::getCanteenId, windows.getCanteenId())
            );
            // 将结果复制给结果类
            CollectDishResultDto colletDish = dishConverter.dish2ColletDish(dish);

            canteenConverter.canteen2collectDish(canteen,colletDish);

            colletDish.setFloorName(windows.getFloorName());
            colletDish.setWinName(windows.getWinName());

            colletDish.setCollectedTime(collection.getUpdateTime());

            colletDish.setDistance(
                    DistanceUtil.getDistance(
                            dto.getLongitude(),
                            dto.getLongitude(),
                            canteen.getCanteenPositionLongitude().doubleValue(),
                            canteen.getCanteenPositionLatitude().doubleValue()
                    )
            );

            colletDish.setCollectedType(collection.getCollectType());

            return colletDish;

        };

        // 获取收藏信息列表
        return this.list(wrapper)
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
                            collectFunc = collectDishFunc;break;
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
}




