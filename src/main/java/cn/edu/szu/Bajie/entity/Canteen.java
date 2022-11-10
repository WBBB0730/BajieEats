package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 餐厅表
 * @TableName canteen
 */
@TableName(value ="canteen")
@Data
public class Canteen implements Serializable {
    /**
     * 餐厅id
     */
    @TableId(value = "canteen_id", type = IdType.AUTO)
    private Integer canteenId;

    /**
     * 餐厅名
     */
    @TableField(value = "canteen_name")
    private String canteenName;

    /**
     * 餐厅主照片
     */
    @TableField(value = "main_image")
    private String mainImage;

    /**
     * 餐厅地址
     */
    @TableField(value = "canteen_address")
    private String canteenAddress;

    /**
     * 餐厅坐标经度
     */
    @TableField(value = "canteen_position_longitude")
    private BigDecimal canteenPositionLongitude;

    /**
     * 餐厅坐标纬度
     */
    @TableField(value = "canteen_position_latitude")
    private BigDecimal canteenPositionLatitude;

    /**
     * 营业时间
     */
    @TableField(value = "opening_time")
    private String openingTime;

    /**
     * 推荐状态
     */
    @TableField(value = "recommend_status")
    private Integer recommendStatus;

    /**
     * 删除状态
     */
    @TableField(value = "delete_status")
    @TableLogic
    private Integer deleteStatus;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 评分
     */
    @TableField(value = "score")
    private BigDecimal score;

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