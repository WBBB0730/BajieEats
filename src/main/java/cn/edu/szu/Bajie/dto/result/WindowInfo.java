package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Dish;
import lombok.Data;
import java.util.List;
@Data
public class WindowInfo {

    private Integer windowId;

    private String windowName;

    private Integer openingStatus;

    private List<Dish> dishList;
}
