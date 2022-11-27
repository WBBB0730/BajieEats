package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.common.CommonPage;
import cn.edu.szu.Bajie.dto.result.DishCommentResultDto;
import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Whitence
* @description 针对表【comment(评论表)】的数据库操作Service
* @createDate 2022-11-09 15:12:39
*/
public interface CommentService extends IService<Comment> {


    List<UserCommentsResultDto> getUserComments(String userId);

    CommonPage<DishCommentResultDto> getDishComments(Integer dishId,Integer pageIndex,Integer pageSize);
}
