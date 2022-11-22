package cn.edu.szu.Bajie.dto.query;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CollectionsQueryDto implements Serializable {

    private List<Integer> types;

    private Integer status;

    private Integer distance;

    private Integer sortType;

    private double longitude;

    private double latitude;

}
