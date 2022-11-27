package cn.edu.szu.Bajie.dto.result;

import cn.edu.szu.Bajie.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class DishCommentResultDto extends Comment {


    private List<String> commentUrls;

    private String nickName;

    private String avatarUrl;

}
