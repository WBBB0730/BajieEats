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

    private List<DishUrl> urlList;

    private Integer isCollected;

}