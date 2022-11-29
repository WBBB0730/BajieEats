package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.constant.CacheConstant;
import cn.edu.szu.Bajie.constant.CacheTimeInterval;
import cn.edu.szu.Bajie.converter.CanteenConverter;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.entity.CanteenDynamic;
import cn.edu.szu.Bajie.entity.CanteenUrl;
import cn.edu.szu.Bajie.service.CanteenDynamicService;
import cn.edu.szu.Bajie.service.CanteenUrlService;
import cn.edu.szu.Bajie.util.CacheService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.mapper.CanteenMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【canteen(餐厅基本信息表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@AllArgsConstructor
@CacheConfig(cacheNames = "canteen")
public class CanteenServiceImpl extends ServiceImpl<CanteenMapper, Canteen>
    implements CanteenService{

    private CanteenUrlService canteenUrlService;

    private CanteenDynamicService canteenDynamicService;

    private CanteenConverter canteenConverter;

    private CacheService cacheService;

    @Override
    @Cacheable(key = "#canteenId")
    public Canteen getBaseCanteenById(Long canteenId) {
        return this.getById(canteenId);
    }

    @Override
    public CanteenDetailResultDto getCanteenDetail(Long canteenId) {
        // redisKey
        String redisKey = MessageFormat.format(CacheConstant.CANTEEN_DETAIL, canteenId);

        // 定义逻辑
        Supplier<CanteenDetailResultDto> supplier = ()->{
            // 餐厅基本信息
            Canteen canteen = getBaseCanteenById(canteenId);
            // 创建结果类
            CanteenDetailResultDto resultDto = canteenConverter.canteen2canteenDetail(canteen);
            // 获取餐厅动态数据并设置
            CanteenDynamic canteenDynamic = canteenDynamicService.getById(canteenId);
            canteenConverter.canteenDynamic2canteenDetail(canteenDynamic,resultDto);
            // 获取照片
            resultDto.setCanteenUrls(
                    canteenUrlService.list(
                            new LambdaQueryWrapper<CanteenUrl>()
                                    .eq(CanteenUrl::getCanteenId, canteenId)
                    )
                            .stream()
                            .map(CanteenUrl::getUrl)
                            .collect(Collectors.toList())

            );
            return resultDto;
        };
        // 缓存
        CanteenDetailResultDto result = cacheService.getByCacheObj(
                redisKey,
                supplier,
                CanteenDetailResultDto.class,
                CacheTimeInterval.ONE_MIN
        );

        // 获取收藏状态


        return result;
    }


}




