package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.common.IErrorCode;
import cn.edu.szu.Bajie.common.OpenIdHolder;
import cn.edu.szu.Bajie.common.ResultCode;
import cn.edu.szu.Bajie.dto.update.UpdateUserDto;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/9 15:34
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /**
     * 登录
     * @param code
     * @return
     */
    @GetMapping("/login")
    public CommonResult<String> login(@RequestParam("code") String code){
        return CommonResult.success(userService.login(code));
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public CommonResult<String> update(@RequestBody @Validated UpdateUserDto updateUserDto){

        userService.update(
                User.builder()
                        .nickName(updateUserDto.getNickName())
                        .avatarUrl(updateUserDto.getAvatarUrl())
                        .build(),
                new LambdaQueryWrapper<User>()
                        .eq(User::getOpenId,OpenIdHolder.openIdThreadLocal.get())
        );

        return CommonResult.success("更新成功");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping
    public CommonResult<User> get(){
        return CommonResult.success(userService.getUserInfo(OpenIdHolder.openIdThreadLocal.get()));
    }
}
