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
 * 菜品
 * @TableName dish
 */
public class DishAddDto implements Serializable {

    /**
     * 菜品名字
     */
    private String dishName;

    /**
     * 菜品图片
     */
    private String dishImage;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 标签
     */
    private String tags;

    /**
     * 窗口id
     */
    private Integer winId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 主料
     */
    private String ingredients;


}