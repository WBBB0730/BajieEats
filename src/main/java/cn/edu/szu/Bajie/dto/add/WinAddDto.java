package cn.edu.szu.Bajie.dto.add;

import java.io.Serializable;

/**
 * 窗口表
 * @TableName windows
 */
public class WinAddDto implements Serializable {

    /**
     * 窗口名
     */
    private String winName;

    /**
     * 营业状态
     */
    private Integer openingStatus;

    /**
     * 餐厅id
     */
    private Integer canteenId;

    /**
     * 楼层id
     */
    private Integer floorId;

    /**
     * 排序
     */
    private Integer sort;

}