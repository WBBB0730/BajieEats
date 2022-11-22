package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 窗口表
 * @TableName windows
 */
@TableName(value ="windows")
@Data
public class Windows implements Serializable {
    /**
     * 窗口id
     */
    @TableId(value = "win_id", type = IdType.AUTO)
    private Integer winId;

    /**
     * 窗口名
     */
    @TableField(value = "win_name")
    private String winName;

    /**
     * 营业状态
     */
    @TableField(value = "is_opening")
    private Integer isOpening;

    /**
     * 餐厅id
     */
    @TableField(value = "canteen_id")
    private Integer canteenId;

    /**
     * 楼层id
     */
    @TableField(value = "floor_name")
    private String floorName;

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