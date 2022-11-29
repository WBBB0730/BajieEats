package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.CollectCanteenResultDto;
import cn.edu.szu.Bajie.dto.result.CollectDishResultDto;
import cn.edu.szu.Bajie.dto.result.CollectWindowResultDto;
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

}
