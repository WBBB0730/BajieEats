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
 * 餐厅图片
 * @TableName canteen_url
 */
@TableName(value ="canteen_url")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanteenUrl implements Serializable {
    /**
     * 数据id
     */
    @TableId(value = "data_id", type = IdType.AUTO)
    private Integer dataId;

    /**
     * 餐厅id
     */
    @TableField(value = "canteen_id")
    private Long canteenId;

    /**
     * url
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