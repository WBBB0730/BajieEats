package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Canteen;

import java.math.BigDecimal;
import java.util.List;

import cn.edu.szu.Bajie.entity.CanteenUrl;
import cn.edu.szu.Bajie.entity.Windows;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanteenDetailResultDto extends Canteen {


    /**
     * 推荐状态
     */
    private Integer recommendStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 是否营业
     */
    private Integer isOpening;

    /**
     * 公告
     */
    private String announce;

    /**
     * 餐厅照片url
     */
    private List<String> canteenUrls;

    private Integer isCollected;


}
