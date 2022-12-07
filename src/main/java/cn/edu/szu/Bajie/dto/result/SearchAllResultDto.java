package cn.edu.szu.Bajie.dto.result;

import java.io.Serializable;
import java.math.BigDecimal;

public class SearchAllResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 餐厅id
     */
    private Long canteenId;
    /**
     * 餐厅名
     */
    private String canteenName;
    /**
     * 餐厅照片
     */
    private String mainImage;
    /**
     * 餐厅地址
     */
    private String canteenAddress;

    /**
     * 经度
     */
    private BigDecimal canteenPositionLongitude;
    /**
     * 纬度
     */
    private BigDecimal canteenPositionLatitude;

    /**
     * 评分
     */
    private BigDecimal score;
    /**
     * 是否营业
     */
    private Integer isOpening;

    /**
     * 营业时间
     */
    private String openingTime;
    /**
     * 公告
     */
    private String announce;
    /**
     * 距离
     */
    private double distance;
}
