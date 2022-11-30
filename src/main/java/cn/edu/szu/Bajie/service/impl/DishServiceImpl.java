package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.converter.DishConverter;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.DishDynamic;
import cn.edu.szu.Bajie.entity.DishUrl;
import cn.edu.szu.Bajie.service.DishDynamicService;
import cn.edu.szu.Bajie.service.DishUrlService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.mapper.DishMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【dish(菜品基本信息表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@CacheConfig(cacheNames = "dish")
@AllArgsConstructor
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    private DishDynamicService dishDynamicService;

    private DishUrlService dishUrlService;

    private DishConverter dishConverter;

    @Override
    @Cacheable(key = "#dishId",unless = "#result == null")
    public Dish getBaseDishInfo(Long dishId) {
        return this.getById(dishId);
    }

    @Override
    @Cacheable(key = "'dishes::'+#winId")
    public List<Dish> getBaseDishesByWinId(Long winId) {
        return this.list(
                new LambdaQueryWrapper<Dish>()
                        .eq(Dish::getWinId,winId)
        );
    }

    @Override
    public List<SimpleDishResultDto> getDishesByWinId(Long winId){

        return getBaseDishesByWinId(winId)
                .stream()
                .map(dish -> {
                    SimpleDishResultDto resultDto = dishConverter.dish2simpleDish(dish);

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

        Dish dish = getBaseDishInfo(dishId);

        DishDetailResultDto resultDto = dishConverter.dish2DishDetail(dish);

        DishDynamic dishDynamic = dishDynamicService.getOne(
                new LambdaQueryWrapper<DishDynamic>()
                        .eq(DishDynamic::getDishId, dish)
        );

        dishConverter.dishDynamic2DishDetail(dishDynamic,resultDto);

        resultDto.setUrlList(getDishUrls(dishId));

        return resultDto;
    }

    @Override
    @Cacheable(key = "'dishUrls::'+#dishId")
    public List<String> getDishUrls(Long dishId) {
        return dishUrlService
                .list(
                        new LambdaQueryWrapper<DishUrl>()
                                .eq(DishUrl::getDishId,dishId)
                )
                .stream()
                .map(DishUrl::getUrl)
                .collect(Collectors.toList());
    }


}




