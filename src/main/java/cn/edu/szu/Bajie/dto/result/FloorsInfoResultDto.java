package cn.edu.szu.Bajie.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;


@Data
@AllArgsConstructor
public class FloorsInfoResultDto {

    private Integer floorId;

    private List<WindowInfo> windowList;

}
