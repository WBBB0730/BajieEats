package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜品动态信息表
 * @TableName dish_dynamic
 */
@TableName(value ="dish_dynamic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDynamic implements Serializable {
    /**
     * 菜品id
     */
    @TableId(value = "dish_id", type = IdType.AUTO)
    private Long dishId;

    /**
     * 标签
     */
    @TableField(value = "tags")
    private String tags;

    /**
     * 推荐状态
     */
    @TableField(value = "recommend_status")
    private Integer recommendStatus;

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
     * 在售状态
     */
    @TableField(value = "on_sale_status")
    private Integer onSaleStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}