package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 数据id
     */
    @TableId(value = "data_id")
    private Integer dataId;

    /**
     * wxOpenId
     */
    @TableField(value = "open_id")
    private String openId;

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
     * 电话号码
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 背景图片
     */
    @TableField(value = "background_image")
    private String backgroundImage;

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