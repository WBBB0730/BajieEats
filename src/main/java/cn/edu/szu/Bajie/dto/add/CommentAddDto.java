package cn.edu.szu.Bajie.dto.add;

import java.io.Serializable;

/**
 * 评论表
 * @TableName comment
 */
public class CommentAddDto implements Serializable {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 图片url
     */
    private String avatarUrl;

    /**
     * 评论内容
     */
    private String content;

    /**
     * wxOpenId
     */
    private String openId;

    /**
     * 菜品id
     */
    private Integer dishId;

}