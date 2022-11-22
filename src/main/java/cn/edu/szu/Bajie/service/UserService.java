package cn.edu.szu.Bajie.service;

import cn.edu.szu.Bajie.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Whitence
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-09 15:12:39
*/
public interface UserService extends IService<User> {

    String login(String code);
}
