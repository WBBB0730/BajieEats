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

        String userId = response.getHeader("userId");

        // 查询条件
        LambdaQueryWrapper<Collection> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Collection::getOpenId,userId)
                .eq(Collection::getCollectType,dto.getType())
                .eq(Collection::getTargetId,dto.getTargetId());
        // 准备实体
        Collection collection = new Collection();
        collection.setCollectType(dto.getType());
        collection.setIsCollected(dto.getIsCollected());
        collection.setOpenId(userId);
        collection.setTargetId(dto.getTargetId());
        // 保存或更新
        collectionService.saveOrUpdate(collection,wrapper);

        return  CommonResult.success("操作成功");
    }


    @PostMapping("/list")
    public CommonResult<List<Object>> list(@RequestBody @Validated CollectionsQueryDto dto,HttpServletResponse response){

        String userId = response.getHeader("userId");

        return CommonResult.success(collectionService.getCollections(dto,userId));

    }
}
