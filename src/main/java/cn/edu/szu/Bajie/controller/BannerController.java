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

    @PostMapping
    public CommonResult<String> add(@RequestBody Banner banner){

        bannerService.save(banner);

        return CommonResult.success("添加成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Banner banner){

        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getBannerId,banner.getBannerId());

        bannerService.update(banner,wrapper);

        return CommonResult.success("更新成功");
    }
    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("bannerId") String bannerId){

        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getBannerId,bannerId);

        return CommonResult.success("删除成功");
    }

    @GetMapping
    public CommonResult<Banner> get(@RequestParam("bannerId") String bannerId){

        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getBannerId,bannerId);

        Banner banner = bannerService.getOne(wrapper);

        return CommonResult.success(banner);
    }

    @GetMapping("/list")
    public CommonResult<List<Banner>> list(){

        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.gt(Banner::getExpireTime,new Date())
                .orderByAsc(Banner::getSort);
        List<Banner> list = bannerService.list(wrapper);
        return CommonResult.success(list);
    }
}
