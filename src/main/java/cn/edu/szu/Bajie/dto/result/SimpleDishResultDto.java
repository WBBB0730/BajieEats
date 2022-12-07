package cn.edu.szu.Bajie.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SimpleDishResultDto implements Serializable {

    /**
     * 菜品id
     */
    private Integer dishId;

    /**
     * 菜品名字
     */
    private String dishName;

    /**
     * 菜品图片
     */
    private String dishImage;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 标签
     */
    private String tags;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 评分
     */
    private BigDecimal score;
}
