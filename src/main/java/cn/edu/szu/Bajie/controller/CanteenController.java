package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
import cn.edu.szu.Bajie.entity.Banner;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.service.CanteenService;
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
@RequestMapping("/canteen")
@AllArgsConstructor
public class CanteenController {

    private CanteenService canteenService;

    @PostMapping
    public CommonResult<String> add(@RequestBody Canteen canteen){

        canteenService.save(canteen);

        return CommonResult.success("添加成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Canteen canteen){

        LambdaQueryWrapper<Canteen> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Canteen::getCanteenId,canteen.getCanteenId());

        canteenService.update(canteen,wrapper);

        return CommonResult.success("更新成功");
    }
    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("canteenId") Integer canteenId){

        LambdaQueryWrapper<Canteen> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Canteen::getCanteenId,canteenId);

        return CommonResult.success("删除成功");
    }

    @GetMapping
    public CommonResult<CanteenDetailResultDto> get(@RequestParam("canteenId") Integer canteenId){

        CanteenDetailResultDto canteenInfo = canteenService.getCanteenInfo(canteenId);

        return CommonResult.success(canteenInfo);

    }

    @PostMapping("/list")
    public CommonResult<List<SimpleCanteenResultDto>> list(@RequestBody CanteenListQueryDto dto){

        List<SimpleCanteenResultDto> list = canteenService.list(dto);

        return CommonResult.success(list);
    }


}
