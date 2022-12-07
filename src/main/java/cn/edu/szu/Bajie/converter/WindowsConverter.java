package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.*;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.CanteenDynamic;
import cn.edu.szu.Bajie.entity.Windows;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WindowsConverter {

    WindowsConverter INSTANCE = Mappers.getMapper(WindowsConverter.class);

    WindowInfo window2windowInfo(Windows windows);

    void window2CollectWindow(Windows windows,@MappingTarget CollectWindowResultDto collectWindow);

    void window2CollectDish(Windows windows,@MappingTarget CollectDishResultDto colletDish);
}
