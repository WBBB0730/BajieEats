package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.mapper.DishMapper;
import org.springframework.stereotype.Service;

/**
* @author Whitence
* @description 针对表【dish(菜品基本信息表)】的数据库操作Service实现
* @createDate 2022-11-26 17:14:25
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

}




