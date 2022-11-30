package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.entity.Windows;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Whitence
* @description 针对表【windows(窗口表)】的数据库操作Service
* @createDate 2022-11-28 21:23:23
*/
public interface WindowsService extends IService<Windows> {

    Windows getWindowInfo(Long winId);

    List<Windows> getWindowsByCanteenId(Long canteenId);
}
