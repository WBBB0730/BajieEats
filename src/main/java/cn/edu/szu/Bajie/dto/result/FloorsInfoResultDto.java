package cn.edu.szu.Bajie.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FloorsInfoResultDto implements Serializable {

    private String floorName;

    private List<WindowInfo> windowList;

}
