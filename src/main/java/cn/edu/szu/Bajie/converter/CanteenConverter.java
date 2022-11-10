package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CanteenConverter {

    CanteenConverter INSTANCE = Mappers.getMapper(CanteenConverter.class);

    CanteenDetailResultDto canteen2canteenDetail(Canteen canteen);

}
