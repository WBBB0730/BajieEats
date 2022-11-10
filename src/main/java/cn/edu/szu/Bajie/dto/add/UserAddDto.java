package cn.edu.szu.Bajie.dto.add;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user
 */
public class UserAddDto implements Serializable {

    /**
     * wxOpenId
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 图片url
     */
    private String avatarUrl;

    /**
     * 电话号码
     */
    private String phoneNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}