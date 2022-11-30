package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Windows;
import cn.edu.szu.Bajie.service.WindowsService;
import cn.edu.szu.Bajie.mapper.WindowsMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Whitence
* @description 针对表【windows(窗口表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@CacheConfig(cacheNames = "windows")
public class WindowsServiceImpl extends ServiceImpl<WindowsMapper, Windows>
    implements WindowsService{

    @Override
    @Cacheable(key = "'windowInfo::'+#winId",unless = "#result == null")
    public Windows getWindowInfo(Long winId) {
        return this.getById(winId);
    }

    @Override
    @Cacheable(key = "'windowList::'+#canteenId")
    public List<Windows> getWindowsByCanteenId(Long canteenId) {
        return this.list(
                new LambdaQueryWrapper<Windows>()
                        .eq(Windows::getCanteenId,canteenId)
        );
    }


}




