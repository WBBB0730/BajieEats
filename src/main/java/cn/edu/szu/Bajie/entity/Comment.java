package cn.edu.szu.Bajie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 评论表
 * @TableName comment
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Comment implements Serializable {
    /**
     * 评论
     */
    @MongoId
    private Long commentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞
     */
    private Integer likes = 0;

    /**
     * wxOpenId
     */
    private String openId;

    /**
     * 创建时间
     */
    private Date createTime =new Date();


    /**
     * 是否可见
     */
    private boolean visible = true;

    /**
     * 删除状态
     */
    private boolean deleted = false;


    /**
     * 点赞用户
     */
    private List<String> likeUsers =new LinkedList<>();

    @JsonIgnore
    private static final long serialVersionUID = 1L;
}