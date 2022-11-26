package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.dto.result.BriefDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Whitence
* @description 针对表【dish(菜品)】的数据库操作Service
* @createDate 2022-11-09 15:12:39
*/
public interface DishService extends IService<Dish> {

    List<Dish> getDishList(Integer winId);

    DishDetailResultDto getDishDetail(Integer dishId);

}
