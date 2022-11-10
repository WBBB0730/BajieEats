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

    @PostMapping
    public CommonResult<String> add(@RequestBody Dish dish){

            dishService.save(dish);

        return CommonResult.success("添加成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Dish dish){

        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getDishId,dish.getDishId());

        dishService.update(dish,wrapper);

        return CommonResult.success("更新成功");
    }
    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("dishId") Integer dishId){

        dishService.removeById(dishId);

        return CommonResult.success("删除成功");
    }

    @GetMapping("/list")
    public CommonResult<List<Dish>> list(@RequestParam("winId") Integer winId){

        List<Dish> dishList = dishService.getDishList(winId);

        return CommonResult.success(dishList);

    }

    @GetMapping
    public CommonResult<DishDetailResultDto> get(@RequestParam("dishId") Integer dishId){

        DishDetailResultDto dishDetail = dishService.getDishDetail(dishId);

        return CommonResult.success(dishDetail);

    }


}
