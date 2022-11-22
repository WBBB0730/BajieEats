package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DishConverter {

    DishConverter INSTANCE = Mappers.getMapper(DishConverter.class);

    SimpleDishResultDto dish2simpleDish(Dish dish);

    DishDetailResultDto dish2DishDetail(Dish dish);

    CollectDishResultDto dish2ColletDish(Dish dish);



}
