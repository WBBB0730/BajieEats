package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.*;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.CanteenDynamic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CanteenConverter {

    CanteenConverter INSTANCE = Mappers.getMapper(CanteenConverter.class);

    CanteenDetailResultDto canteen2canteenDetail(Canteen canteen);

    void canteenDynamic2canteenDetail(CanteenDynamic canteenDynamic,@MappingTarget CanteenDetailResultDto canteenDetailResultDto);


    CollectCanteenResultDto canteen2CollectCanteen(Canteen canteen);

    void canteenDynamic2CollectCanteen(CanteenDynamic canteenDynamic,@MappingTarget CollectCanteenResultDto collectCanteenResultDto);


    SimpleCanteenResultDto canteen2SimpleCanteen(Canteen canteen);

    void canteenDynamic2SimpleCanteen(CanteenDynamic canteenDynamic,@MappingTarget SimpleCanteenResultDto simpleCanteenResultDto);

    CollectWindowResultDto canteen2collectWindow(Canteen canteen);

    void canteen2collectDish(Canteen canteen, @MappingTarget CollectDishResultDto colletDish);
}
