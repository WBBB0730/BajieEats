package cn.edu.szu.Bajie.dto.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BriefDishResultDto implements Serializable {


    private Integer dishId;
    /**
     * 菜品名
     */
    private String dishName;

    /**
     * 菜品照片
     */
    private String dishImage;

    /**
     * 餐厅名
     */
    private String canteenName;

    /**
     * 餐厅地址
     */
    private String canteenAddress;


    private String floorName;

    /**
     * 窗口名
     */
    private String winName;


}
