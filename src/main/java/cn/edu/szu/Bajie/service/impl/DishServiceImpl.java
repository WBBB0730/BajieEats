package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.constant.CacheConstant;
import cn.edu.szu.Bajie.constant.CacheTimeInterval;
import cn.edu.szu.Bajie.converter.DishConverter;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.DishDynamic;
import cn.edu.szu.Bajie.entity.DishUrl;
import cn.edu.szu.Bajie.service.DishDynamicService;
import cn.edu.szu.Bajie.service.DishUrlService;
import cn.edu.szu.Bajie.util.CacheService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.mapper.DishMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【dish(菜品基本信息表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@AllArgsConstructor
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    private DishDynamicService dishDynamicService;

    private DishUrlService dishUrlService;

    private DishConverter dishConverter;

    private CacheService cacheService;

    @Override
    public Dish getBaseDishInfo(Long dishId) {
        // 获取菜品基本信息并缓存
        return cacheService
                .getByCacheObj(
                        MessageFormat.format(CacheConstant.DISH_BASE,dishId),
                        ()->this.getById(dishId),
                        Dish.class,
                        CacheTimeInterval.AN_HOUR
                );
    }

    @Override
    public List<Dish> getBaseDishesByWinId(Long winId) {
        // 获取菜品基本信息并缓存
        return cacheService
                .getByCacheList(
                        MessageFormat.format(CacheConstant.DISH_BASES,winId),
                        ()->this.list(
                                new LambdaQueryWrapper<Dish>()
                                        .eq(Dish::getWinId,winId)
                        ),
                        Dish.class,
                        CacheTimeInterval.AN_HOUR
                );
    }

    @Override
    public List<SimpleDishResultDto> getDishesByWinId(Long winId){

        return getBaseDishesByWinId(winId)
                .stream()
                .map(dish -> {
                    SimpleDishResultDto resultDto = dishConverter.dish2simpleDish(dish);
                    // 获取菜品动态信息
                    DishDynamic dishDynamic = dishDynamicService.getOne(
                            new LambdaQueryWrapper<DishDynamic>()
                                    .eq(DishDynamic::getDishId, dish.getDishId())
                    );

                    dishConverter.dishDynamic2SimpleDish(dishDynamic,resultDto);

                    return resultDto;
                })
                .collect(Collectors.toList());

    }

    @Override
    public DishDetailResultDto getDishDetail(Long dishId) {
        // 获取基本信息
        Dish dish = getBaseDishInfo(dishId);

        if(ObjectUtil.isNull(dish)){
            return null;
        }
        // 准备详细信息
        DishDetailResultDto resultDto = dishConverter.dish2DishDetail(dish);
        // 获取动态信息
        DishDynamic dishDynamic = dishDynamicService.getOne(
                new LambdaQueryWrapper<DishDynamic>()
                        .eq(DishDynamic::getDishId, dish.getDishId())
        );
        // 赋值动态信息
        dishConverter.dishDynamic2DishDetail(dishDynamic,resultDto);
        // 菜品URL
        resultDto.setUrlList(getDishUrls(dishId));

        return resultDto;
    }

    @Override
    public List<String> getDishUrls(Long dishId) {
        return cacheService.getByCacheList(
                MessageFormat.format(CacheConstant.DISH_URLS,dishId),
                ()->dishUrlService
                        .list(
                                new LambdaQueryWrapper<DishUrl>()
                                        .eq(DishUrl::getDishId,dishId)
                        )
                        .stream()
                        .map(DishUrl::getUrl)
                        .collect(Collectors.toList()),
                String.class,
                CacheTimeInterval.AN_HOUR
        );
    }


}




