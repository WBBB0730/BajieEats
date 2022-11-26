package cn.edu.szu.Bajie.dto.query;

import lombok.Data;

import java.io.Serializable;
@Data
public class CanteenListQueryDto implements Serializable {

    private double longitude;

    private double latitude;

    private Integer sortType = 0;

}
