package cn.edu.szu.Bajie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.service.UserService;
import cn.edu.szu.Bajie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Whitence
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-09 15:12:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




