package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Collection;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/29 19:12
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectCanteenResultDto implements Collection.ICollectionHelper {

    /**
     * 收藏类型
     */
    private Integer collectedType;

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
     * 是否营业
     */
    private Integer isOpening;
    /**
     * 公告
     */
    private String announce;

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
        return PinyinUtil.getPinyin(canteenName,"");
    }

}
