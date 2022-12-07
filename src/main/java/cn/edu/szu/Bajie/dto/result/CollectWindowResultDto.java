package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.entity.Dish;
import cn.hutool.extra.pinyin.PinyinUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class CollectWindowResultDto  implements Collection.ICollectionHelper , Serializable {
    /**
     * 收藏类型
     */
    private Integer collectedType;
    /**
     * 窗口id
     */
    private Long winId;
    /**
     * 窗口名
     */
    private String winName;
    /**
     * 是否营业
     */
    private Integer isOpening;

    /**
     * 餐厅id
     */
    private Long canteenId;
    /**
     * 餐厅名
     */
    private String canteenName;
    /**
     * 餐厅地址
     */
    private String canteenAddress;
    /**
     * 餐厅经度
     */
    private BigDecimal canteenPositionLongitude;
    /**
     * 餐厅纬度
     */
    private BigDecimal canteenPositionLatitude;

    /**
     * 距离
     */
    private double distance;
    /**
     * 收藏时间
     */
    private Date collectedTime;
    /**
     * 菜品id
     */
    private List<Dish> dishes;


    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public long getTimeStamp() {
        return collectedTime.getTime();
    }

    @Override
    public String getName() {
        return PinyinUtil.getPinyin(winName,"");
    }
}
