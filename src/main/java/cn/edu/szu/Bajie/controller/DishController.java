package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/9 15:31
 * @version 1.0
 */
@RestController
@RequestMapping("/dish")
@AllArgsConstructor
public class DishController {

    private DishService dishService;

    /**
     * 获取窗口下所有的菜品
     * @param winId
     * @return
     */

    @GetMapping("/list")
    public CommonResult<List<Dish>> list(@RequestParam("winId") Integer winId){

        List<Dish> dishList = dishService.getDishList(winId);

        return CommonResult.success(dishList);

    }

    /**
     * 获取菜品详情
     * @param dishId
     * @return
     */
    @GetMapping
    public CommonResult<DishDetailResultDto> get(@RequestParam("dishId") Integer dishId){

        DishDetailResultDto dishDetail = dishService.getDishDetail(dishId);

        return CommonResult.success(dishDetail);

    }


}
