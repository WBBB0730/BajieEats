package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.common.OpenIdHolder;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.FloorsInfoResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
import cn.edu.szu.Bajie.entity.Banner;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.service.CollectionService;
import cn.edu.szu.Bajie.util.CacheService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

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
    public CommonResult<CanteenDetailResultDto> get(@RequestParam("canteenId") Long canteenId){

        CanteenDetailResultDto res = canteenService.getCanteenDetail(canteenId);
        // 设置收藏状态
        res.setIsCollected(collectionService.getCollectStatus(Collection.CollectionType.CANTEEN.getValue(),canteenId));

        return CommonResult.success(res);
    }

    /**
     * 获取餐厅列表
     * @param dto
     * @return
     */

    @PostMapping("/list")
    public CommonResult<List<SimpleCanteenResultDto>> list(@RequestBody @Validated CanteenListQueryDto dto){
        return CommonResult.success(
                // 获取餐厅列表
                canteenService.getCanteenList(dto)
                        .stream()
                        .peek(simpleCanteenResultDto ->
                                // 设置收藏状态
                                simpleCanteenResultDto.setIsCollected(
                                        collectionService.getCollectStatus(
                                                Collection.CollectionType.CANTEEN.getValue(),
                                                simpleCanteenResultDto.getCanteenId()
                                        )
                                ))
                        .collect(Collectors.toList())
        );
    }

    /**
     * 获取餐厅楼层以及菜品信息
     * @param canteenId
     * @return
     */

    @GetMapping("/getFloorList")
    public CommonResult<List<FloorsInfoResultDto>> getFloors(@RequestParam("canteenId") Long canteenId){

        return CommonResult.success(
                canteenService.getFloorsInfo(canteenId)
                        .stream()
                        .peek(floorsInfoResultDto -> {
                            floorsInfoResultDto
                                    .getWindowList()
                                    .forEach(windowInfo -> {
                                        // 设置收藏状态
                                        windowInfo.setIsCollected(
                                                collectionService
                                                        .getCollectStatus(
                                                                Collection.CollectionType.WINDOW.getValue(),
                                                                windowInfo.getWinId()
                                                        )
                                        );
                                    });
                        })
                        .collect(Collectors.toList())
        );
    }
}
