package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Whitence
* @description 针对表【dish(菜品基本信息表)】的数据库操作Service
* @createDate 2022-11-28 21:23:23
*/
public interface DishService extends IService<Dish> {

    Dish getBaseDishInfo(Long dish);

    List<Dish> getBaseDishesByWinId(Long winId);

    List<String> getDishUrls(Long dishId);

    List<SimpleDishResultDto> getDishesByWinId(Long winId);

    DishDetailResultDto getDishDetail(Long dishId);

}
