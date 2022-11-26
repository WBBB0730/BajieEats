package cn.edu.szu.Bajie.dto.result;


import cn.edu.szu.Bajie.entity.Comment;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import java.util.List;
import java.io.Serializable;

@Data
public class UserCommentsResultDto extends Comment implements Serializable {


    /**
     * 昵称
     */
    private String nickName;

    /**
     * 图片url
     */
    private String avatarUrl;

    /**
     * 评论url
     */
    private List<String> commentUrls;

    /**
     * 餐厅名
     */
    private String canteenName;

    /**
     * 餐厅地址
     */
    private String canteenAddress;


    /**
     * 窗口名
     */
    private String winName;

    /**
     * 菜品名
     */
    private String dishName;

    /**
     * 菜品照片
     */
    private String dishImage;


}
