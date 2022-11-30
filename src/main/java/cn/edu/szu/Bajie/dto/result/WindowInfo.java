package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Dish;
import lombok.Data;
import java.util.List;
@Data
public class WindowInfo {

    private Long winId;

    private String winName;

    private Integer isOpening;

    private Integer isCollected;

    private List<SimpleDishResultDto> dishes;
}
