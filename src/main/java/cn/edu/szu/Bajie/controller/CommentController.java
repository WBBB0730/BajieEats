package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonPage;
import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.add.CommentAddDto;
import cn.edu.szu.Bajie.dto.result.UserCommentsResultDto;
import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.entity.CommentUrl;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.CommentService;
import cn.edu.szu.Bajie.service.CommentUrlService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/9 15:33
 * @version 1.0
 */
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {

    private CommentService commentService;

    private CommentUrlService commentUrlService;

    /**
     * 获取菜品评价列表
     * @param dishId
     * @param pageIndex
     * @param pageSize
     * @return
     */

    @GetMapping("/list")
    public CommonResult<CommonPage<Comment>> list(@RequestParam("dishId") Integer dishId,
                                     @RequestParam("pageIndex") Integer pageIndex,
                                     @RequestParam("pageSize") Integer pageSize){

        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getDishId,dishId);
        // 分页获取
        Page<Comment> page = new Page<>(pageIndex, pageSize);
        commentService.page(page,wrapper);

        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 发布评论
     * @param dto
     * @param response
     * @return
     */

    @PostMapping
    public CommonResult<String> add(@RequestBody CommentAddDto dto, HttpServletResponse response){

        String userId = response.getHeader("userId");
        // 准备实体类
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setDishId(dto.getDishId());
        comment.setLikes(0);
        comment.setScore(dto.getScore());
        comment.setOpenId(userId);
        // 保存
        commentService.save(comment);

        // 保存url
        List<CommentUrl> urlList = dto.getCommentUrls()
                .stream()
                .map(url -> {
                    CommentUrl commentUrl = new CommentUrl();
                    commentUrl.setCommentId(comment.getCommentId());
                    commentUrl.setUrl(url);
                    return commentUrl;
                }).collect(Collectors.toList());

        commentUrlService.saveBatch(urlList);

        return CommonResult.success("添加成功");

    }

    @GetMapping("/user")
    public CommonResult<List<UserCommentsResultDto>> userComments(HttpServletResponse response){

        String userId = response.getHeader("userId");

        return CommonResult.success(commentService.getUserComments(userId));
    }



}
