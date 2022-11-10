package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.entity.Dish;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CommonResult<String> add(@RequestBody Dish dish){



        return CommonResult.success("添加成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Dish dish){



        return CommonResult.success("更新成功");
    }
    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("dishId") Integer dishId){



        return CommonResult.success("删除成功");
    }
}
