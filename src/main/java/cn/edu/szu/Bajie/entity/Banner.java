package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 轮播图
 * @TableName banner
 */
@TableName(value ="banner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {
    /**
     * 轮播图id
     */
    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer bannerId;

    /**
     * 轮播图url
     */
    @TableField(value = "banner_url")
    private String bannerUrl;

    /**
     * 跳转url
     */
    @TableField(value = "redirect_url")
    private String redirectUrl;

    /**
     * 权重
     */
    @TableField(value = "weight")
    private Integer weight;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 过期时间
     */
    @TableField(value = "expire_time")
    private Date expireTime;

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