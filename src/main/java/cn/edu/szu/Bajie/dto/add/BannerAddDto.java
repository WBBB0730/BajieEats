package cn.edu.szu.Bajie.dto.add;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 * @TableName banner
 */
public class BannerAddDto implements Serializable {

    /**
     * 轮播图url
     */
    private String bannerUrl;

    /**
     * 跳转url
     */
    private String redirectUrl;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 过期时间
     */
    private Date expireTime;

}