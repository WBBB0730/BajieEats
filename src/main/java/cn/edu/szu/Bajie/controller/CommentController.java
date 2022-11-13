package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonPage;
import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public CommonResult<String> add(@RequestBody Dish dish){



        return CommonResult.success("添加成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Dish dish){



        return CommonResult.success("更新成功");
    }
    @GetMapping("/list")
    public CommonResult<CommonPage<Comment>> list(@RequestParam("dishId") Integer dishId,
                                     @RequestParam("pageIndex") Integer pageIndex,
                                     @RequestParam("pageSize") Integer pageSize){

        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getDishId,dishId);

        Page<Comment> page = new Page<>(pageIndex, pageSize);

        commentService.page(page,wrapper);

        return CommonResult.success(CommonPage.restPage(page));
    }


}
