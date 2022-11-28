package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Canteen;
import cn.edu.szu.Bajie.service.CanteenService;
import cn.edu.szu.Bajie.mapper.CanteenMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
* @author Whitence
* @description 针对表【canteen(餐厅基本信息表)】的数据库操作Service实现
* @createDate 2022-11-26 17:14:25
*/
@Service
@CacheConfig(cacheNames = "canteen")
@AllArgsConstructor
public class CanteenServiceImpl extends ServiceImpl<CanteenMapper, Canteen>
    implements CanteenService{


    @Override
    @Cacheable(key = "#canteenId")
    public Canteen getCanteenById(Long canteenId) {
        return this.getById(canteenId);
    }
}




