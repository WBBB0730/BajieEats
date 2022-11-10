package cn.edu.szu.Bajie.mapper;

import cn.edu.szu.Bajie.dto.result.SimpleCanteenResultDto;
import cn.edu.szu.Bajie.entity.Canteen;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Whitence
* @description 针对表【canteen(餐厅表)】的数据库操作Mapper
* @createDate 2022-11-09 15:12:39
* @Entity cn.edu.szu.Bajie.entity.Canteen
*/
public interface CanteenMapper extends BaseMapper<Canteen> {

    /**
     *
     * @param pageIndex
     * @param pageSize
     * @param sortType
     * @return
     */
    List<SimpleCanteenResultDto> getCanteens(
            @Param("pageIndex") Integer pageIndex,
            @Param("pageSize") Integer pageSize,
            @Param("sortType") String sortType,
            @Param("longitude") double longitude,
            @Param("latitude") double latitude);

}




