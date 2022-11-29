package cn.edu.szu.Bajie.converter;

import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.Comment;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T23:46:44+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_281 (Oracle Corporation)"
)
@Component
public class CommentConverterImpl implements CommentConverter {

    @Override
    public UserCommentsResultDto comment2UserComment(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        UserCommentsResultDto userCommentsResultDto = new UserCommentsResultDto();

        userCommentsResultDto.setCommentId( comment.getCommentId() );
        userCommentsResultDto.setContent( comment.getContent() );
        userCommentsResultDto.setLikes( comment.getLikes() );
        userCommentsResultDto.setScore( comment.getScore() );
        userCommentsResultDto.setOpenId( comment.getOpenId() );
        userCommentsResultDto.setDishId( comment.getDishId() );
        userCommentsResultDto.setCreateTime( comment.getCreateTime() );
        userCommentsResultDto.setUpdateTime( comment.getUpdateTime() );

        return userCommentsResultDto;
    }
}
