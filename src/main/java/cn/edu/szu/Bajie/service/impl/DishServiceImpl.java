package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.service.DishDynamicService;
import cn.edu.szu.Bajie.service.DishUrlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.mapper.DishMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
* @author Whitence
* @description 针对表【dish(菜品基本信息表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@CacheConfig(cacheNames = "dish")
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    private DishDynamicService dishDynamicService;

    private DishUrlService dishUrlService;

    @Override
    @Cacheable(key = "#dishId",unless = "#result == null")
    public Dish getDishInfo(Long dishId) {
        return this.getById(dishId);
    }
}




