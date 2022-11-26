package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.result.BriefDishResultDto;
import cn.edu.szu.Bajie.dto.result.DishDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleDishResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.Windows;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.CollectionService;
import cn.edu.szu.Bajie.service.DishService;
import cn.edu.szu.Bajie.service.WindowsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

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

    private WindowsService windowsService;

    private CanteenService canteenService;

    private CollectionService collectionService;

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
    public CommonResult<DishDetailResultDto> get(@RequestParam("dishId") Integer dishId, HttpServletResponse response){

        String userId = response.getHeader("userId");

        DishDetailResultDto dishDetail = dishService.getDishDetail(dishId);

        dishDetail.setIsCollected(
                (int)collectionService.count(
                        new LambdaQueryWrapper<Collection>()
                                .eq(Collection::getOpenId, userId)
                                .eq(Collection::getCollectType, 2)
                                .eq(Collection::getTargetId, dishId)
                )
        );

        return CommonResult.success(dishDetail);

    }

    @GetMapping("/simple")
    public CommonResult<BriefDishResultDto> getSimple(@RequestParam("dishId") Integer dishId){

        BriefDishResultDto briefDishResultDto = new BriefDishResultDto();

        Dish dish = dishService.getOne(
                new LambdaQueryWrapper<Dish>()
                        .eq(Dish::getDishId, dishId)
        );
        if(Objects.isNull(dish)){
            return CommonResult.failed("菜品不存在");
        }

        Windows windows = windowsService.getOne(
                new LambdaQueryWrapper<Windows>()
                        .eq(Windows::getWinId, dish.getWinId())
        );

        Canteen canteen = canteenService.getOne(
                new LambdaQueryWrapper<Canteen>()
                        .eq(Canteen::getCanteenId, windows.getCanteenId())
        );

        briefDishResultDto.setDishId(dishId);
        briefDishResultDto.setDishImage(dish.getDishImage());
        briefDishResultDto.setDishName(dish.getDishName());

        briefDishResultDto.setCanteenName(canteen.getCanteenName());
        briefDishResultDto.setCanteenAddress(canteen.getCanteenAddress());

        briefDishResultDto.setFloorName(windows.getFloorName());
        briefDishResultDto.setWinName(windows.getWinName());

        return CommonResult.success(briefDishResultDto);

    }


}
