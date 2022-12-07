package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName collection
 */
@TableName(value ="collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
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
//    @TableLogic(value = "1",delval = "0")
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

    public interface ICollectionHelper{

         double getDistance();


         long getTimeStamp();


         String getName();
    }

    public enum CollectionType{
        CANTEEN(0),
        WINDOW(1),
        DISH(2);

        private int value;

        CollectionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}