package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.constant.CacheConstant;
import cn.edu.szu.Bajie.constant.CacheTimeInterval;
import cn.edu.szu.Bajie.converter.CanteenConverter;
import cn.edu.szu.Bajie.converter.WindowsConverter;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.*;
import cn.edu.szu.Bajie.entity.*;
import cn.edu.szu.Bajie.service.*;
import cn.edu.szu.Bajie.util.CacheService;
import cn.edu.szu.Bajie.util.DistanceUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.mapper.CanteenMapper;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.seg.common.Term;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.*;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    private WindowsService windowsService;

    private WindowsConverter windowsConverter;

    private DishService dishService;

    private CacheService cacheService;

    private TokenizeService tokenizeService;

    @Override
    public Canteen getBaseCanteenById(Long canteenId) {
        return cacheService.getByCacheObj(
                MessageFormat.format(CacheConstant.CANTEEN_BASE,canteenId),
                ()->this.getById(canteenId),
                Canteen.class,
                CacheTimeInterval.AN_HOUR
        );
    }

    @Override
    public CanteenDetailResultDto getCanteenDetail(Long canteenId) {
        // redisKey
        String redisKey = MessageFormat.format(CacheConstant.CANTEEN_DETAIL, canteenId);

        // 定义逻辑
        Supplier<CanteenDetailResultDto> supplier = ()->{
            // 餐厅基本信息
            Canteen canteen = getBaseCanteenById(canteenId);

            if(ObjectUtil.isNull(canteen)){
                return null;
            }
            // 创建结果类
            CanteenDetailResultDto resultDto = canteenConverter.canteen2canteenDetail(canteen);
            // 获取餐厅动态数据并设置
            CanteenDynamic canteenDynamic = canteenDynamicService.getById(canteenId);
            canteenConverter.canteenDynamic2canteenDetail(canteenDynamic,resultDto);
            // 获取照片
            resultDto.setCanteenUrls(getCanteenUrls(canteenId));
            return resultDto;
        };
        // 缓存
        CanteenDetailResultDto result = cacheService.getByCacheObj(
                redisKey,
                supplier,
                CanteenDetailResultDto.class,
                CacheTimeInterval.ONE_MIN
        );

        return result;
    }

    @Override
    public List<SimpleCanteenResultDto> getCanteenList(CanteenListQueryDto dto) {

         Comparator<SimpleCanteenResultDto> comparing = null;

        switch (dto.getSortType()){
            case 0:
                comparing=Comparator.comparing(SimpleCanteenResultDto::getScore);break;
            case 1:
                comparing=Comparator.comparing(SimpleCanteenResultDto::getScore).reversed();break;
            case 2:
                comparing=Comparator.comparing(SimpleCanteenResultDto::getDistance);break;
            case 3:
                comparing=Comparator.comparing(SimpleCanteenResultDto::getDistance).reversed();break;
            default:
        }

        return canteenDynamicService
                .list()
                .stream()
                .map(canteenDynamic -> {

                    Canteen canteen = this.getBaseCanteenById(canteenDynamic.getCanteenId());

                    SimpleCanteenResultDto simpleCanteenResultDto = canteenConverter.canteen2SimpleCanteen(canteen);

                    canteenConverter.canteenDynamic2SimpleCanteen(canteenDynamic,simpleCanteenResultDto);

                    simpleCanteenResultDto.setDistance(
                            // 计算距离
                            (int) DistanceUtil.getDistance(
                                    dto.getLongitude(),
                                    dto.getLongitude(),
                                    canteen.getCanteenPositionLongitude().doubleValue(),
                                    canteen.getCanteenPositionLatitude().doubleValue()
                            )
                    );

                    return simpleCanteenResultDto;
                })
                .sorted(comparing)
                .collect(Collectors.toList());

    }

    @Override
    public List<FloorsInfoResultDto> getFloorsInfo(Long canteenId) {
        return windowsService
                // 获取餐厅窗口
                .getWindowsByCanteenId(canteenId)
                .stream()
                // 分组转为map存储
                .collect(Collectors.groupingBy(Windows::getFloorName,Collectors.toList()))
                // 遍历所有entrySet
                .entrySet()
                .stream()
                .map(entry ->{
                    // 准备结果类
                    FloorsInfoResultDto resultDto = new FloorsInfoResultDto();
                    // 设置楼层
                    resultDto.setFloorName(entry.getKey());
                    // 设置窗口信息
                    resultDto.setWindowList(
                            //
                            entry.getValue()
                                    .stream()
                                    .map(windows -> {
                                        WindowInfo windowInfo = windowsConverter.window2windowInfo(windows);

                                        windowInfo.setDishes(
                                                dishService.getDishesByWinId(windows.getWinId())
                                        );
                                        return windowInfo;
                                    })
                                    .collect(Collectors.toList())
                    );
                    return resultDto;
                })
                .sorted(Comparator.comparing(FloorsInfoResultDto::getFloorName))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getCanteenUrls(Long canteenId) {
        return cacheService
                .getByCacheList(
                        MessageFormat.format(CacheConstant.CANTEEN_URLS,canteenId),
                        ()->canteenUrlService.list(
                                        new LambdaQueryWrapper<CanteenUrl>()
                                                .eq(CanteenUrl::getCanteenId,canteenId)
                                )
                                .stream()
                                .map(CanteenUrl::getUrl)
                                .collect(Collectors.toList()),
                        String.class,
                        CacheTimeInterval.AN_HOUR
                );
    }

    @Override
    @Cacheable(key = "'breifDishInfo'+#dishId")
    public BriefDishResultDto getSimpleDishResult(Long dishId) {

        Dish baseDishInfo = dishService.getBaseDishInfo(dishId);

        Windows windowInfo = windowsService.getWindowInfo(baseDishInfo.getWinId());

        Canteen canteen = this.getBaseCanteenById(windowInfo.getCanteenId());

        return BriefDishResultDto
                .builder()
                .dishId(baseDishInfo.getDishId())
                .dishName(baseDishInfo.getDishName())
                .dishImage(baseDishInfo.getDishImage())
                .canteenName(canteen.getCanteenName())
                .canteenAddress(canteen.getCanteenAddress())
                .floorName(windowInfo.getFloorName())
                .winName(windowInfo.getWinName())
                .build();

    }

    @Override
    public List<SearchAllResultDto> searchAll(String keyWord) {

        Set<String> parse = tokenizeService.parse(keyWord);




        return null;
    }




}




