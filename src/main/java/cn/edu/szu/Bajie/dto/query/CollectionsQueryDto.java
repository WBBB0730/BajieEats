package cn.edu.szu.Bajie.dto.query;

import cn.edu.szu.Bajie.annotation.ValidatorDiy;
import cn.edu.szu.Bajie.util.CustomizeValidator;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

@Data
public class CollectionsQueryDto implements Serializable {

    @ValidatorDiy(message = "收藏类型不合法",methodNames = "checkCollectTypes",predicts = CustomizeValidator.class)
    private List<Integer> types;

    @ValidatorDiy(message = "营业状态只能是0或1",methodNames = "checkZeroOrOne",predicts = CustomizeValidator.class)
    private Integer status;

    private Integer distance;

    @ValidatorDiy(message = "排序类型不合法",methodNames = "checkCollectListSortType",predicts = CustomizeValidator.class)
    private Integer sortType;

    @Max(180)
    @Min(-180)
    private double longitude;
    @Max(90)
    @Min(-90)
    private double latitude;

}
