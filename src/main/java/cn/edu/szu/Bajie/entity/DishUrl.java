package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 菜品图片url
 * @TableName dish_url
 */
@TableName(value ="dish_url")
@Data
public class DishUrl implements Serializable {
    /**
     * 数据id
     */
    @TableId(value = "data_id")
    private Integer dataId;

    /**
     * 菜品id
     */
    @TableField(value = "dish_id")
    private Long dishId;

    /**
     * 菜品图片url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}