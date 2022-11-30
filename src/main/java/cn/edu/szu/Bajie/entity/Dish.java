package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 菜品基本信息表
 * @TableName dish
 */
@TableName(value ="dish")
@Data
public class Dish implements Serializable {
    /**
     * 菜品id
     */
    @TableId(value = "dish_id", type = IdType.AUTO)
    private Long dishId;

    /**
     * 菜品名字
     */
    @TableField(value = "dish_name")
    private String dishName;

    /**
     * 菜品图片
     */
    @TableField(value = "dish_image")
    private String dishImage;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 窗口id
     */
    @TableField(value = "win_id")
    private Long winId;

    /**
     * 主料
     */
    @TableField(value = "ingredients")
    private String ingredients;

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