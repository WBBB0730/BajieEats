package cn.edu.szu.Bajie.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Whitence
 * @description: TODO
 * @date 2022/10/10 21:25
 */

@FeignClient(url = "https://api.weixin.qq.com",name = "wx")
public interface WxFeignClient {

    /**
     * 远程调用获取用户信息
     * @param appid
     * @param secret
     * @param jsCode
     * @param granType
     * @return
     */
    @GetMapping("/sns/jscode2session")
    String getUserString(@RequestParam("appid") String appid,
                         @RequestParam("secret") String secret,
                         @RequestParam("js_code") String jsCode,
                         @RequestParam("grant_type") String granType
    );
}

