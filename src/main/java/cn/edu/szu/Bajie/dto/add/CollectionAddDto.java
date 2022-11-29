package cn.edu.szu.Bajie.dto.add;

import cn.edu.szu.Bajie.annotation.ValidatorDiy;
import cn.edu.szu.Bajie.util.CustomizeValidator;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Data
public class CollectionAddDto implements Serializable {

    @ValidatorDiy(message = "收藏类型不合法！",methodNames = "checkCollectType",predicts = CustomizeValidator.class)
    private Integer type;

    private Long targetId;

    @ValidatorDiy(message = "收藏标志只能是0或者1",methodNames = "checkIsCollected",predicts = CustomizeValidator.class)
    private Integer isCollected;
}
