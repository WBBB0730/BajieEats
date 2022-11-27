package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.util.PinyinUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class CollectWindowResultDto  implements Collection.ICollectionHelper {

    private Integer collectedType;

    private Integer winId;

    private String winName;
    private Integer isOpening;

    private String canteenName;

    private String canteenAddress;

    private BigDecimal canteenPositionLongitude;

    private BigDecimal canteenPositionLatitude;


    private double distance;

    private Date collectedTime;

    private List<Dish> dishs;


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
        return PinyinUtil.toPinyin(winName);
    }
}
