package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.FloorsInfoResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
import cn.edu.szu.Bajie.entity.Banner;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.CollectionService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/9 15:31
 * @version 1.0
 */
@RestController
@RequestMapping("/canteen")
@AllArgsConstructor
public class CanteenController {

    private CanteenService canteenService;

    private CollectionService collectionService;


    /**
     * 获取餐厅详情信息
     * @param canteenId
     * @return
     */

    @GetMapping
    public CommonResult<CanteenDetailResultDto> get(@RequestParam("canteenId") Integer canteenId,HttpServletResponse response){

        String userId = response.getHeader("userId");

        CanteenDetailResultDto canteenInfo = canteenService.getCanteenInfo(canteenId);

        canteenInfo.setIsCollected(
                (int)collectionService.count(
                        new LambdaQueryWrapper<Collection>()
                                .eq(Collection::getTargetId,canteenId)
                                .eq(Collection::getCollectType,0)
                                .eq(Collection::getOpenId,userId)
                                .eq(Collection::getIsCollected,1)

                )
        );


        return CommonResult.success(canteenInfo);

    }

    /**
     * 获取餐厅列表
     * @param dto
     * @return
     */

    @PostMapping("/list")
    public CommonResult<List<SimpleCanteenResultDto>> list(@RequestBody CanteenListQueryDto dto, HttpServletResponse response){
        // 获取用户id
        String userId = response.getHeader("userId");

        // 获取餐厅信息
        List<SimpleCanteenResultDto> list = canteenService
                .list(dto);

        // 判断该用户是否收藏该商店
                list
                        .forEach(item->{
                            long count = collectionService.count(
                                    new LambdaQueryWrapper<Collection>()
                                            .eq(Collection::getOpenId, userId)
                                            .eq(Collection::getCollectType, 0)
                                            .eq(Collection::getTargetId, item.getCanteenId())
                                            .eq(Collection::getIsCollected,1)
                            );
                            item.setIsCollected((int) count);
                        });



        return CommonResult.success(list);
    }

    /**
     * 获取餐厅楼层以及菜品信息
     * @param canteenId
     * @return
     */

    @GetMapping("/getFloorList")
    public CommonResult<List<FloorsInfoResultDto>> getFloors(@RequestParam("canteenId") Integer canteenId,HttpServletResponse response){

        String userId = response.getHeader("userId");

        List<FloorsInfoResultDto> result = canteenService.getFloorList(canteenId);

        result
                .forEach(floorsInfoResultDto -> {
                    floorsInfoResultDto
                            .getWindowList()
                            .forEach(windowInfo -> {
                                windowInfo.setIsCollected(
                                        (int)collectionService.count(
                                                new LambdaQueryWrapper<Collection>()
                                                        .eq(Collection::getOpenId, userId)
                                                        .eq(Collection::getCollectType, 1)
                                                        .eq(Collection::getTargetId, windowInfo.getWindowId())
                                                        .eq(Collection::getIsCollected,1)
                                        )
                                );
                            });
                });

        return CommonResult.success(result);
    }
}
