package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.dto.result.WxResultDto;
import cn.edu.szu.Bajie.feign.WxFeignClient;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.service.UserService;
import cn.edu.szu.Bajie.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
* @author Whitence
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-09 15:12:39
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private WxFeignClient wxFeignClient;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.app_secure}")
    private String appidSecure;
    @Override
    public User getUserInfo(String code) {

        String string = wxFeignClient.getUserString(appid, appidSecure, code, "authorization_code");

        WxResultDto wxResultDto = JSONObject.parseObject(string, WxResultDto.class);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId,wxResultDto.getOpenid());

        User user = this.getOne(wrapper);

        if(Objects.isNull(user)){

            user = User.builder()
                    .nickName("微信用户"+ UUID.randomUUID().toString().substring(0,5))
                    .avatarUrl("https://img2.baidu.com/it/u=3094149767,177600321&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1668272400&t=9657735b8f76a8acd269ceee1649bd82")
                    .openId(wxResultDto.getOpenid())
                    .phoneNumber("xxxx")
                    .build();

            this.save(user);

        }

        return user;
    }
}




