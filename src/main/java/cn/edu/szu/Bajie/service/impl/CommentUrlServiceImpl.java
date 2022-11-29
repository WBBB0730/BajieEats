package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.CommentUrl;
import cn.edu.szu.Bajie.service.CommentUrlService;
import cn.edu.szu.Bajie.mapper.CommentUrlMapper;
import org.springframework.stereotype.Service;

/**
* @author Whitence
* @description 针对表【comment_url(评论url)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
public class CommentUrlServiceImpl extends ServiceImpl<CommentUrlMapper, CommentUrl>
    implements CommentUrlService{

}




