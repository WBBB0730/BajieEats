package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 餐厅基本信息表
 * @TableName canteen
 */
@TableName(value ="canteen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Canteen implements Serializable {
    /**
     * 餐厅id
     */
    @TableId(value = "canteen_id", type = IdType.AUTO)
    private Long canteenId;

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
     * 删除状态
     */
    @TableField(value = "delete_status")
    @TableLogic
    private Integer deleteStatus;

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