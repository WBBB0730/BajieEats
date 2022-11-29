package cn.edu.szu.Bajie.dto.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.util.Date;

public class CollectionCacheDto implements Serializable {

    /**
     * id
     */
    private Integer collectId;

    /**
     * 收藏类型
     */
    private Integer collectType;

    /**
     * 目标id
     */
    private Long targetId;

    /**
     * 是否收藏
     */
    private Integer isCollected;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否修改
     */
    private Integer isChanged;
}
