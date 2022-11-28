package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Whitence
* @description 针对表【banner(轮播图)】的数据库操作Service
* @createDate 2022-11-26 17:14:25
*/
public interface BannerService extends IService<Banner> {
    List<Banner> getBanners();
}
