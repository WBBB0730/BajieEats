package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.entity.CommentDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 评论表
 * @TableName comment
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserCommentsResultDto extends CommentDish {


    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 简单菜品
     */
    private BriefDishResultDto dishInfo;

}