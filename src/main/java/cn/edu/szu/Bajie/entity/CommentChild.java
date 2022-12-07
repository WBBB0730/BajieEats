package cn.edu.szu.Bajie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * 子评论评论表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentChild extends Comment {

    /**
     * 父评论id
     */
    @Indexed
    private Long parentId;

    /**
     * 艾特对象Id
     */
    @Indexed
    private String replyUserId;

    @Field
    private static final long serialVersionUID = 1L;
}