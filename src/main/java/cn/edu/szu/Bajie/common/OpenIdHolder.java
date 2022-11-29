package cn.edu.szu.Bajie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/28 23:00
 * @version 1.0
 */
@Slf4j
public class OpenIdHolder {

    public static InheritableThreadLocal<String> openIdThreadLocal = new InheritableThreadLocal();

}
