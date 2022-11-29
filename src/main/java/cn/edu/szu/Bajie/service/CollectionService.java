package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.dto.add.CollectionAddDto;
import cn.edu.szu.Bajie.dto.query.CollectionsQueryDto;
import cn.edu.szu.Bajie.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
/**
* @author Whitence
* @description 针对表【collection】的数据库操作Service
* @createDate 2022-11-28 21:23:23
*/
public interface CollectionService extends IService<Collection> {


    List<Collection> getUserCollections(String userId);


    void doCollect(CollectionAddDto dto);

    List<Object> getCollectionInfos(CollectionsQueryDto dto);

    Integer getCollectStatus(Integer type,Long targetId);

     
}
