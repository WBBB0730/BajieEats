package cn.edu.szu.Bajie.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class FloorsInfoResultDto implements Serializable {

    private String floorName;

    private List<WindowInfo> windowList;

}
