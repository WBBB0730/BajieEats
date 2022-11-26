package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName collection
 */
@TableName(value ="collection")
@Data
public class Collection implements Serializable {
    /**
     * 收藏id
     */
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    /**
     * 收藏类型
     */
    @TableField(value = "collect_type")
    private Integer collectType;

    /**
     * 目标id
     */
    @TableField(value = "target_id")
    private Long targetId;

    /**
     * 是否收藏
     */
    @TableField(value = "is_collected")
    private Integer isCollected;

    /**
     * 用户id
     */
    @TableField(value = "open_id")
    private String openId;

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