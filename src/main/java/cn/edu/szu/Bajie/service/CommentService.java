package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.common.CommonPage;
import cn.edu.szu.Bajie.dto.add.CommentAddDto;
import cn.edu.szu.Bajie.dto.result.DishCommentsResultDto;
import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.entity.CommentDish;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Whitence
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2022-11-28 21:23:23
*/
public interface CommentService {

    void commentDish(CommentAddDto dto);

    CommonPage<DishCommentsResultDto> getDishComments(Long dishId, Integer page, Integer pageSize);

    CommonPage<UserCommentsResultDto> getUserComments(Integer page, Integer pageSize);

    void doLike(Long commentId);
}
