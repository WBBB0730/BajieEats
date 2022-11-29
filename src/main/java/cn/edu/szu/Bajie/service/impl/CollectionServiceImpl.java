package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.common.OpenIdHolder;
import cn.edu.szu.Bajie.constant.CacheConstant;
import cn.edu.szu.Bajie.constant.CacheTimeInterval;
import cn.edu.szu.Bajie.dto.add.CollectionAddDto;
import cn.edu.szu.Bajie.dto.query.CollectionsQueryDto;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.service.WindowsService;
import cn.edu.szu.Bajie.util.CacheService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.service.CollectionService;
import cn.edu.szu.Bajie.mapper.CollectionMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【collection】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@AllArgsConstructor
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection>
    implements CollectionService{

    private CanteenService canteenService;

    private WindowsService windowsService;

    private DishService dishService;


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
        // 获取用户id
        String userId = OpenIdHolder.openIdThreadLocal.get();
        // 更新缓存
        cacheService.setList(
                // redisKey
                MessageFormat.format(CacheConstant.COLLECTION_LIST,userId),
                // 获取用户收藏列表
                getUserCollections(userId)
                        .stream()
                        .peek(collection -> {
                            // 修改用户收藏状态
                            if(collection.getCollectType().equals(dto.getType()) && collection.getTargetId().equals(dto.getTargetId())){
                                // 更新收藏状态
                                collection.setIsCollected(ObjectUtil.isNull(dto.getIsCollected())?collection.getIsCollected()^1:dto.getIsCollected());

                                // 添加到消息队列更新数据库
                                cacheService.lPushObj(CacheConstant.COLLECTION_MQ,collection);
                            }
                        }).collect(Collectors.toList()),
                CacheTimeInterval.AN_HOUR
        );

    }

    /**
     * 每5分钟清空消息队列
     */
    @Scheduled(cron = "0 */5 * * * ?")
    private void updateToDatabase(){
        cacheService
                .range(CacheConstant.COLLECTION_MQ, 0, -1, Collection.class)
                .forEach(collection -> {
                    this.saveOrUpdate(
                            collection,
                            new LambdaQueryWrapper<Collection>()
                                    .eq(Collection::getCollectId,collection.getCollectId())
                    );
                });
    }

    @Override
    public List<Object> getCollectionInfos(CollectionsQueryDto dto) {


        return null;
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




