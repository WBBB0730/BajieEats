package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.dto.query.CollectionsQueryDto;
import cn.edu.szu.Bajie.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.List;

/**
* @author Whitence
* @description 针对表【collection】的数据库操作Service
* @createDate 2022-11-16 14:18:14
*/
public interface CollectionService extends IService<Collection> {

    List<Object> getCollections(CollectionsQueryDto dto,String userId);
}
