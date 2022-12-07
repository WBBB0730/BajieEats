package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.result.BriefDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.CollectionService;
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

    private CanteenService canteenService;

    private CollectionService collectionService;

    /**
     * 获取窗口下所有的菜品
     * @param winId
     * @return
     */

    @GetMapping("/list")
    public CommonResult<List<Dish>> list(@RequestParam("winId") Long winId){

        return null;

    }

    /**
     * 获取菜品详情
     * @param dishId
     * @return
     */
    @GetMapping
    public CommonResult<DishDetailResultDto> get(@RequestParam("dishId") Long dishId){
        DishDetailResultDto res = dishService.getDishDetail(dishId);
        // 设置收藏状态
        res.setIsCollected(
                collectionService
                        .getCollectStatus(Collection.CollectionType.DISH.getValue(), dishId)
        );
        return CommonResult.success(res );

    }

    /**
     * 获取菜品简单信息
     */
    @GetMapping("/simple")
    public CommonResult<BriefDishResultDto> getSimple(@RequestParam("dishId") Long dishId){

        return CommonResult.success(canteenService.getSimpleDishResult(dishId));
    }


}
