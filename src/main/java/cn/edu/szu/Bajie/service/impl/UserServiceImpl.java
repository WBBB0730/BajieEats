package cn.edu.szu.Bajie.service.impl;

import cn.edu.szu.Bajie.dto.result.WxResultDto;
import cn.edu.szu.Bajie.feign.WxFeignClient;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.service.UserService;
import cn.edu.szu.Bajie.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
* @author Whitence
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-28 21:23:23
*/
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private WxFeignClient wxFeignClient;

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.app_secure}")
    private String appidSecure;

    @Value("${jwt.alg}")
    private String alg;

    @Value("${jwt.key}")
    private String key;

    @Override
    public String login(String code) {

        // 远程调用wxapi获取当前用户的openid
        String string = wxFeignClient.getUserString(appid, appidSecure, code, "authorization_code");

        WxResultDto wxResultDto = JSONObject.parseObject(string, WxResultDto.class);

        // 登陆失败
        if(StrUtil.isBlank(wxResultDto.getOpenid())){
            return "登陆失败";
        }
        // 根据用户openid获取用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId,wxResultDto.getOpenid());

        User user = this.getOne(wrapper);

        // 自动注册新用户
        if(Objects.isNull(user)){
            user = User.builder()
                    .nickName("微信用户"+ UUID.randomUUID().toString().substring(0,5))
                    .avatarUrl("https://img2.baidu.com/it/u=3094149767,177600321&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1668272400&t=9657735b8f76a8acd269ceee1649bd82")
                    .backgroundImage("https://img2.baidu.com/it/u=3094149767,177600321&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1668272400&t=9657735b8f76a8acd269ceee1649bd82")
                    .openId(wxResultDto.getOpenid())
                    .phoneNumber("xxxx")
                    .build();
            this.save(user);
        }
        // 根据用户id生成jwt并返回
        JWTSigner signer = JWTSignerUtil.createSigner(alg,key.getBytes(StandardCharsets.UTF_8));

        Map<String,Object> payload =new HashMap<>();

        payload.put("userId",user.getOpenId());

        return JWTUtil.createToken(payload, signer);
    }

    @Override
    @Cacheable(key = "#openId",unless = "#result == null")
    public User getUserInfo(String openId) {

        return this.getOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getOpenId,openId)
        );

    }

    public static void main(String[] args) {
        // 根据用户id生成jwt并返回
        JWTSigner signer = JWTSignerUtil.createSigner("hs256","bajiechifan".getBytes(StandardCharsets.UTF_8));

        Map<String,Object> payload =new HashMap<>();

        payload.put("userId","oetvJ4mOihIShp4Pq7wh-DmsLsj0");

        String token = JWTUtil.createToken(payload, signer);

        System.out.println(token);
    }
}




