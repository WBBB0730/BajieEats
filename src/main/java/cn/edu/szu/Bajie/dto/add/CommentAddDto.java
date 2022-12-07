package cn.edu.szu.Bajie.dto.add;

import cn.edu.szu.Bajie.annotation.CheckSensitive;
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
    @CheckSensitive(message = "评论含有敏感词汇")
    private String content;

    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 评论照片
     */
    private List<String> commentUrls;

}