package cn.edu.szu.Bajie.dto.add;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
/**
 * 评论表
 * @TableName comment
 */
@Data
public class CommentAddDto implements Serializable {


    /**
     * 评论内容
     */
    private String content;


    /**
     * 菜品id
     */
    private Integer dishId;

    /**
     * 评分
     */
    private BigDecimal score;

    /**
     * 评论照片
     */
    private List<String> commentUrls;

}