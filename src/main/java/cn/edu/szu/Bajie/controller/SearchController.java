package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.result.SearchAllResultDto;
import cn.edu.szu.Bajie.service.CanteenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @description: TODO
 * @author hjc
 * @date 2022/12/7 0:06
 * @version 1.0
 */
@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private CanteenService canteenService;

    @GetMapping("/all")
    public CommonResult<List<SearchAllResultDto>> searchAll(@RequestParam("keyWord") String keyWord){

        return null;
    }

    @GetMapping("/canteen")
    public CommonResult<List<Object>> searchCanteen(@RequestParam("keyWord") String keyWord){

        return null;
    }
}
