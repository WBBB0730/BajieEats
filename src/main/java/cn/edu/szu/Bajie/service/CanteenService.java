package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.dto.query.CanteenListQueryDto;
import cn.edu.szu.Bajie.dto.result.CanteenDetailResultDto;
import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
* @author Whitence
* @description 针对表【canteen(餐厅表)】的数据库操作Service
* @createDate 2022-11-09 15:12:39
*/
public interface CanteenService extends IService<Canteen> {

     List<SimpleCanteenResultDto> list(CanteenListQueryDto dto);


     CanteenDetailResultDto getCanteenInfo(Integer canteenId);

}
