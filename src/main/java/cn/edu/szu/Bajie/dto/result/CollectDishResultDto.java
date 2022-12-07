package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Collection;
import cn.hutool.extra.pinyin.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectDishResultDto implements Collection.ICollectionHelper {

    /**
     * 收藏类型
     */
    private Integer collectedType;
    /**
     * 菜品id
     */
    private Long dishId;
    /**
     * 菜品名
     */
    private String dishName;
    /**
     * 菜品照片
     */
    private String dishImage;
    /**
     * 标签
     */
    private String tags;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 在售状态
     */
    private Integer onSaleStatus;
    /**
     * 餐厅名字
     */
    private String canteenName;
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
     * 楼层名字
     */
    private String floorName;
    /**
     * 窗口名字
     */
    private String winName;
    /**
     * 距离
     */
    private double distance;
    /**
     * 收藏时间
     */
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
        return PinyinUtil.getPinyin(dishName,"");
    }
}
