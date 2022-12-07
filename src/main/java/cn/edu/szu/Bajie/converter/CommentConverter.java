package cn.edu.szu.Bajie.converter;


import cn.edu.szu.Bajie.dto.result.DishCommentsResultDto;
import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.entity.CommentDish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentConverter {

    CommentConverter INSTANCE = Mappers.getMapper(CommentConverter.class);

    UserCommentsResultDto comment2UserComment(CommentDish dish);

    DishCommentsResultDto comment2DishComment(CommentDish dish);





}
