package cn.edu.szu.Bajie.dto.add;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 餐厅表
 * @TableName canteen
 */
public class CanteenAddDto implements Serializable {

    /**
     * 餐厅名
     */
    private String canteenName;

    /**
     * 餐厅主照片
     */
    private String mainImage;

    /**
     * 餐厅地址
     */
    private String canteenAddress;

    /**
     * 餐厅坐标经度
     */
    private BigDecimal canteenPositionLongitude;

    /**
     * 餐厅坐标纬度
     */
    private BigDecimal canteenPositionLatitude;

    /**
     * 营业时间
     */
    private String openingTime;

    /**
     * 排序
     */
    private Integer sort;

}