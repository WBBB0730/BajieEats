package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.converter.DishConverter;
import cn.edu.szu.Bajie.dto.result.BriefDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.DishUrl;
import cn.edu.szu.Bajie.service.DishUrlService;
import cn.edu.szu.Bajie.service.WindowsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.mapper.DishMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author Whitence
* @description 针对表【dish(菜品)】的数据库操作Service实现
* @createDate 2022-11-09 15:12:39
*/
@Service
@AllArgsConstructor
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    private DishUrlService dishUrlService;

    private DishConverter dishConverter;

    @Override
    public List<Dish> getDishList(Integer winId) {
        // 构造条件
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getWinId,winId);
        // 获取
        return this.list(wrapper);
    }

    @Override
    public DishDetailResultDto getDishDetail(Integer dishId) {
        // 获取菜品基本信息
        Dish dish = this.getById(dishId);

        if(Objects.isNull(dish)){
            return null;
        }

        DishDetailResultDto resultDto = dishConverter.dish2DishDetail(dish);

        // 获取菜品图片
        LambdaQueryWrapper<DishUrl> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishUrl::getDishId,dishId);
        List<DishUrl> list = dishUrlService.list(wrapper);

        resultDto.setUrlList(list);

        return resultDto;
    }



}




