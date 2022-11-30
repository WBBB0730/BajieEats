package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.DishUrl;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * 菜品
 * @TableName dish
 */
@TableName(value ="dish")
@Data
public class DishDetailResultDto extends Dish implements Serializable {


    /**
     * 标签
     */
    private String tags;

    /**
     * 推荐状态
     */
    private Integer recommendStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 在售状态
     */
    private Integer onSaleStatus;


    /**
     * 是否收藏
     */
    private Integer isCollected;

    /**
     * 照片
     */

    List<String> urlList;

}