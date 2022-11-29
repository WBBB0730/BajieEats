package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.entity.Banner;
import cn.edu.szu.Bajie.service.BannerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/9 15:30
 * @version 1.0
 */
@RestController
@RequestMapping("/banner")
@AllArgsConstructor
public class BannerController {

    private BannerService bannerService;

    /**
     * 获取轮播图详情
     * @param bannerId
     * @return
     */

    @GetMapping
    public CommonResult<Banner> get(@RequestParam("bannerId") String bannerId){

        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getBannerId,bannerId);

        Banner banner = bannerService.getOne(wrapper);

        return CommonResult.success(banner);
    }

    /**
     * 获取轮播图列表
     * @return
     */
    @GetMapping("/list")
    public CommonResult<List<Banner>> list(){
        return CommonResult.success(bannerService.getBanners());
    }
}
