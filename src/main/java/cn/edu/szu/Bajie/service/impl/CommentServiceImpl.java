package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.Comment;
import cn.edu.szu.Bajie.service.CommentService;
import cn.edu.szu.Bajie.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Whitence
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




