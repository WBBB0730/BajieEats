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
        return null;
    }

    /**
     * 发布评论
     * @param dto
     * @param response
     * @return
     */

    @PostMapping
    public CommonResult<String> add(@RequestBody CommentAddDto dto, HttpServletResponse response){

        return null;

    }

    @GetMapping("/user")
    public CommonResult<List<UserCommentsResultDto>> userComments(HttpServletResponse response){
        return null;
    }



}
