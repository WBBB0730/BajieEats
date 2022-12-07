package cn.edu.szu.Bajie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 评论表
 * @TableName comment
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
@SuperBuilder(toBuilder = true)
public class CommentDish extends Comment {

    /**
     * 评分
     */
    private Integer score;

    /**
     * 菜品id
     */
    @Indexed
    private Long dishId;

    /**
     * 评论照片
     */
    private List<String> commentUrls ;

}