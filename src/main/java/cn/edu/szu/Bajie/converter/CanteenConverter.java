package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.CollectCanteenResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.CollectWindowResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CanteenConverter {

    CanteenConverter INSTANCE = Mappers.getMapper(CanteenConverter.class);

    CanteenDetailResultDto canteen2canteenDetail(Canteen canteen);

    CollectCanteenResultDto canteen2collectCanteen(Canteen canteen);

    CollectWindowResultDto canteen2collectWindow(Canteen canteen);

    void canteen2collectDish(Canteen canteen, @MappingTarget CollectDishResultDto resultDto);

}
