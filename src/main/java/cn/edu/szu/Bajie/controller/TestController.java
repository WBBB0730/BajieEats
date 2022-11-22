package cn.edu.szu.Bajie.controller;

import cn.edu.szu.Bajie.common.CommonResult;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {


    @GetMapping("/getToken")
    public CommonResult<String> getToken(@RequestParam("openId") String openId){

        // 根据用户id生成jwt并返回
        JWTSigner signer = JWTSignerUtil.createSigner("hs256","bajiechifan".getBytes(StandardCharsets.UTF_8));

        Map<String,Object> payload =new HashMap<>();

        payload.put("userId",openId);

        String token = JWTUtil.createToken(payload, signer);

        return CommonResult.success(token);
    }
}
