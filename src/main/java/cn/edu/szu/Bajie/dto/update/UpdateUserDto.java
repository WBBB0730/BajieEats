package cn.edu.szu.Bajie.dto.update;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class UpdateUserDto implements Serializable {

    /**
     * 昵称
     */
    @Length(max = 20,min = 1)
    private String nickName;

    /**
     * 图片url
     */
    @URL
    private String avatarUrl;
//
//    /**
//     * 电话号码
//     */
//    private String phoneNumber;
//
//    /**
//     * 背景图片
//     */
//    private String backgroundImage;



}