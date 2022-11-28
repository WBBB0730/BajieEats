package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Banner;
import cn.edu.szu.Bajie.service.BannerService;
import cn.edu.szu.Bajie.mapper.BannerMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
/**
* @author Whitence
* @description 针对表【banner(轮播图)】的数据库操作Service实现
* @createDate 2022-11-26 17:14:25
*/
@Service
@CacheConfig(cacheNames = "banners")
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner>
    implements BannerService{


    @Override
    @Cacheable(key = "'bannerList'")
    public List<Banner> getBanners() {
        return this.list(
                new LambdaQueryWrapper<Banner>()
                        .gt(Banner::getExpireTime,new Date())
                        .orderByAsc(Banner::getSort)
        );
    }
}




