package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Comment;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SimpleCanteenResultDto implements Serializable {

    private Integer canteenId;

    /**
     * 餐厅名
     */
    private String canteenName;

    /**
     * 餐厅主照片
     */
    private String mainImage;

    /**
     * 餐厅地址
     */
    private String canteenAddress;

    /**
     * 餐厅坐标经度
     */
    private BigDecimal canteenPositionLongitude;

    /**
     * 餐厅坐标纬度
     */
    private BigDecimal canteenPositionLatitude;

    /**
     * 距离
     */
    private Integer distance;

    /**
     * 营业时间
     */
    private String openingTime;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评论
     */
    private String announce;
}
