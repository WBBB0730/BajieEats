package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.edu.szu.Bajie.entity.Dish;
import cn.edu.szu.Bajie.entity.User;
import cn.edu.szu.Bajie.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CommonResult<String> add(@RequestBody Dish dish){



        return CommonResult.success("添加成功");
    }
    @PutMapping
    public CommonResult<String> update(@RequestBody Dish dish){



        return CommonResult.success("更新成功");
    }
    @DeleteMapping
    public CommonResult<String> delete(@RequestParam("dishId") Integer dishId){



        return CommonResult.success("删除成功");
    }

    @GetMapping("/getUserInfo")
    public CommonResult<User> get(@RequestParam("code") String code){
        return CommonResult.success(userService.getUserInfo(code));
    }

    @PostMapping("/updateUser")
    public CommonResult<User> update(@RequestBody User user){

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getOpenId,user.getOpenId());

        userService.update(user, wrapper);

        user  = userService.getOne(wrapper);

        return CommonResult.success(user);
    }
}
