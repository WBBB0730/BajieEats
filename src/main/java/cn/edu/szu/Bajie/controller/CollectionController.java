package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.add.CollectionAddDto;
import cn.edu.szu.Bajie.dto.query.CollectionsQueryDto;
import cn.edu.szu.Bajie.entity.Collection;
import cn.edu.szu.Bajie.service.CollectionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.List;
/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/9 15:33
 * @version 1.0
 */
@RestController
@RequestMapping("/collection")
@AllArgsConstructor
public class CollectionController {

    private CollectionService collectionService;


    /**
     * 收藏or取消收藏
     * @param dto
     * @return
     */

    @PostMapping
    public CommonResult<String> add(@RequestBody @Validated CollectionAddDto dto, HttpServletResponse response){

        return null;
    }


    @PostMapping("/list")
    public CommonResult<List<Object>> list(@RequestBody @Validated CollectionsQueryDto dto,HttpServletResponse response){
        return null;

    }
}
