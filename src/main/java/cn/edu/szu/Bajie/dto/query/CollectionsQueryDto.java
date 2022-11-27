package cn.edu.szu.Bajie.dto.query;

import cn.edu.szu.Bajie.annotation.ValidatorDiy;
import cn.edu.szu.Bajie.util.CustomizeValidator;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CollectionsQueryDto implements Serializable {

    @ValidatorDiy(message = "收藏类型数组不合法！",methodNames = "checkCollectTypes",predicts = CustomizeValidator.class)
    private List<Integer> types;

    private Integer status;

    private Integer distance;

    private Integer sortType;

    private double longitude;

    private double latitude;

}
