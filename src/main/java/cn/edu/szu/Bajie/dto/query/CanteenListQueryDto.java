package cn.edu.szu.Bajie.dto.query;

import cn.edu.szu.Bajie.annotation.ValidatorDiy;
import cn.edu.szu.Bajie.util.CustomizeValidator;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
@Data
public class CanteenListQueryDto implements Serializable {

    @Max(180)
    @Min(-180)
    private double longitude;
    @Max(90)
    @Min(-90)
    private double latitude;

    @ValidatorDiy(methodNames = "checkCanteenListSortType",predicts = CustomizeValidator.class,message = "排序类型不合法")
    private Integer sortType = 0;

}
