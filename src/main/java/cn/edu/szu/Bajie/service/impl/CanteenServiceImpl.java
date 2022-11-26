package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.converter.CanteenConverter;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.FloorsInfoResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
import cn.edu.szu.Bajie.dto.result.WindowInfo;
import cn.edu.szu.Bajie.entity.CanteenUrl;
import cn.edu.szu.Bajie.entity.Windows;
import cn.edu.szu.Bajie.service.CanteenUrlService;
import cn.edu.szu.Bajie.service.WindowsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.mapper.CanteenMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author Whitence
* @description 针对表【canteen(餐厅表)】的数据库操作Service实现
* @createDate 2022-11-09 15:12:39
*/
@Service
@AllArgsConstructor
public class CanteenServiceImpl extends ServiceImpl<CanteenMapper, Canteen>
    implements CanteenService{

    private CanteenConverter canteenConverter;

    private WindowsService windowsService;

    private CanteenUrlService canteenUrlService;

    @Override
    public List<SimpleCanteenResultDto> list(CanteenListQueryDto dto) {

        return this.getBaseMapper()
                .getCanteens(
                        dto.getSortType(),
                        dto.getLongitude(),
                        dto.getLatitude());

    }

    @Override
    public CanteenDetailResultDto getCanteenInfo(Integer canteenId) {
        // 获取餐厅基本信息
        Canteen canteen = this.getById(canteenId);

        if(Objects.isNull(canteen)){
            return null;
        }

        CanteenDetailResultDto resultDto = canteenConverter.canteen2canteenDetail(canteen);

        LambdaQueryWrapper<Windows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Windows::getCanteenId,canteenId);

        // 获取餐厅照片
        LambdaQueryWrapper<CanteenUrl> queryWrapper = new LambdaQueryWrapper<>();
        List<CanteenUrl> canteenUrls = canteenUrlService.list(queryWrapper);

        resultDto.setCanteenUrls(canteenUrls);

        return resultDto;
    }

    @Override
    public List<FloorsInfoResultDto> getFloorList(Integer canteenId) {
        // 查询条件
        LambdaQueryWrapper<Windows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Windows::getCanteenId,canteenId);

        // 用于记录楼层名以及该楼层窗口列表信息映射
        Map<String, List<WindowInfo>> map =new HashMap<>();
        // 获取餐厅楼层
        windowsService.list(wrapper)
                .forEach((item)->{
            map.putIfAbsent(item.getFloorName(),new LinkedList<>());
            // 获取当前楼层的窗口信息
            map.get(item.getFloorName()).add(windowsService.getWinInfo(item.getWinId()));
        });

        return map.entrySet()
                .stream()
                // 转为结果类
                .map((item)->new FloorsInfoResultDto(item.getKey(),item.getValue()))
                // 按照楼层名字排序
                .sorted(Comparator.comparing(FloorsInfoResultDto::getFloorName))
                .collect(Collectors.toList());
    }


}




