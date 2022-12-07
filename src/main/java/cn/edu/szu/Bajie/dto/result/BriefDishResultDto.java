package cn.edu.szu.Bajie.dto.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BriefDishResultDto implements Serializable {
    private String canteenAddress;
    private String canteenName;
    private Long dishId;
    private String dishImage;
    private String dishName;
    private String floorName;
    private String winName;
}
