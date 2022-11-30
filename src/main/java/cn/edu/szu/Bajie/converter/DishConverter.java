package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.DishDynamic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DishConverter {

    DishConverter INSTANCE = Mappers.getMapper(DishConverter.class);

    SimpleDishResultDto dish2simpleDish(Dish dish);

    void dishDynamic2SimpleDish(DishDynamic dishDynamic,@MappingTarget SimpleDishResultDto simpleDishResultDto);

    DishDetailResultDto dish2DishDetail(Dish dish);

    void dishDynamic2DishDetail(DishDynamic dishDynamic,@MappingTarget DishDetailResultDto resultDto);

    CollectDishResultDto dish2ColletDish(Dish dish);



}
