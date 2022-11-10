package cn.edu.szu.Bajie.dto.query;

import lombok.Data;

import java.io.Serializable;
@Data
public class CanteenListQueryDto implements Serializable {

    private Integer pageIndex = 1;

    private Integer pageSize = 10;

    private String sortType = "socre";

}
