package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.util.PinyinUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CollectDishResultDto implements Collection.ICollectionHelper {


    private Integer collectedType;

    private Integer dishId;

    private String dishName;

    private String dishImage;

    private String tags;

    private BigDecimal price;

    private Integer onSaleStatus;

    private String canteenName;

    private String canteenAddress;

    private BigDecimal canteenPositionLongitude;

    private BigDecimal canteenPositionLatitude;

    private String floorName;

    private String winName;

    private double distance;

    private Date collectedTime;


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
        return PinyinUtil.toPinyin(dishName);
    }
}
