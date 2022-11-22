package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Collection;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class CollectCanteenResultDto implements Collection.ICollectionHelper {

    private Integer collectedType;

    private Integer canteenId;

    private String canteenName;

    private String mainImage;

    private String canteenAddress;

    private BigDecimal canteenPositionLongitude;

    private BigDecimal canteenPositionLatitude;

    private Integer isOpening;

    private String announce;

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
        return canteenName;
    }
}
