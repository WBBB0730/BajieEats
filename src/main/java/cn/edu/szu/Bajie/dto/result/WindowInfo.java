package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WindowInfo {

    private Long winId;

    private String winName;

    private Integer isOpening;

    private Integer isCollected;

    private List<SimpleDishResultDto> dishes;
}
