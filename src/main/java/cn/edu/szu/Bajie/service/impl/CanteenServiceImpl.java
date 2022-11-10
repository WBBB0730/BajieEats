package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.converter.CanteenConverter;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
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

import java.util.List;

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

        return this.getBaseMapper().getCanteens(dto.getPageIndex(),dto.getPageSize(), dto.getSortType(),dto.getLongitude(),dto.getLatitude());

    }

    @Override
    public CanteenDetailResultDto getCanteenInfo(Integer canteenId) {

        Canteen canteen = this.getById(canteenId);

        CanteenDetailResultDto resultDto = canteenConverter.canteen2canteenDetail(canteen);

        LambdaQueryWrapper<Windows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Windows::getCanteenId,canteenId);

        List<Windows> windows = windowsService.list(wrapper);

        LambdaQueryWrapper<CanteenUrl> queryWrapper = new LambdaQueryWrapper<>();
        List<CanteenUrl> canteenUrls = canteenUrlService.list(queryWrapper);

        resultDto.setCanteenUrls(canteenUrls);
        resultDto.setWindows(windows);

        return resultDto;
    }


}




