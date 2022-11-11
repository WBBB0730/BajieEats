package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.dto.result.WindowInfo;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Windows;
import cn.edu.szu.Bajie.service.WindowsService;
import cn.edu.szu.Bajie.mapper.WindowsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Whitence
* @description 针对表【windows(窗口表)】的数据库操作Service实现
* @createDate 2022-11-09 15:12:39
*/
@Service
@AllArgsConstructor
public class WindowsServiceImpl extends ServiceImpl<WindowsMapper, Windows>
    implements WindowsService{

    private DishService dishService;

    @Override
    public WindowInfo getWinInfo(Integer winId) {

        Windows windows = this.getById(winId);

        List<Dish> dishList = dishService.getDishList(winId);

        WindowInfo windowInfo = new WindowInfo();

        windowInfo.setWindowId(winId);
        windowInfo.setDishList(dishList);
        windowInfo.setOpeningStatus(windows.getOpeningStatus());
        windowInfo.setWindowName(windows.getWinName());

        return windowInfo;

    }
}




