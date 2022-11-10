package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 评论表
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论
     */
    @TableId(value = "comment_id")
    private Integer commentId;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 图片url
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 点赞
     */
    @TableField(value = "likes")
    private Integer likes;

    /**
     * 评分
     */
    @TableField(value = "score")
    private BigDecimal score;

    /**
     * wxOpenId
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 菜品id
     */
    @TableField(value = "dish_id")
    private Integer dishId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}