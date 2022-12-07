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
 * 餐厅动态信息表
 * @TableName canteen_dynamic
 */
@TableName(value ="canteen_dynamic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanteenDynamic implements Serializable {
    /**
     * 餐厅id
     */
    @TableId(value = "canteen_id", type = IdType.AUTO)
    private Long canteenId;

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
     * 是否营业
     */
    @TableField(value = "is_opening")
    private Integer isOpening;

    /**
     * 公告
     */
    @TableField(value = "announce")
    private String announce;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}