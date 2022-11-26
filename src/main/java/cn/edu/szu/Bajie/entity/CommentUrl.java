package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论url
 * @TableName comment_url
 */
@TableName(value ="comment_url")
@Data
public class CommentUrl implements Serializable {
    /**
     * 数据id
     */
    @TableId(value = "data_id")
    private Integer dataId;

    /**
     * 评论id
     */
    @TableField(value = "comment_id")
    private Integer commentId;

    /**
     * 评论照片url
     */
    @TableField(value = "url")
    private String url;

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