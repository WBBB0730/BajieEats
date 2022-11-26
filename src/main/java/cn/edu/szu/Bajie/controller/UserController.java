package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.common.IErrorCode;
import cn.edu.szu.Bajie.common.ResultCode;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
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
    public CommonResult<String> get(@RequestParam("code") String code){

        return null;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public CommonResult<String> update(@RequestBody User user, HttpServletRequest request){

        return null;
    }
}
