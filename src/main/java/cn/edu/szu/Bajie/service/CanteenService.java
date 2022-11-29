package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Whitence
* @description 针对表【canteen(餐厅基本信息表)】的数据库操作Service
* @createDate 2022-11-28 21:23:23
*/
public interface CanteenService extends IService<Canteen> {

    Canteen getBaseCanteenById(Long canteenId);


    CanteenDetailResultDto getCanteenDetail(Long canteenId);
}
