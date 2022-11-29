package cn.edu.szu.Bajie.filter;

import cn.edu.szu.Bajie.common.OpenIdHolder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/16 17:35
 * @version 1.0
 */
@Component
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if(StrUtil.isBlank(token)){
            filterChain.doFilter(request,response);
            return;
        }
        // 解析token
        JWT jwt = JWTUtil.parseToken(token);
        Map map = jwt.getPayloads().toBean(Map.class);

        if(!map.containsKey("userId")){
            filterChain.doFilter(request,response);
            return;
        }
        String userId = (String) map.get("userId");


        // 根据用户id查看用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        if(Objects.isNull(userDetails)){
            filterChain.doFilter(request,response);
            return;
        }

        //response.addHeader("userId",userId);
        OpenIdHolder.openIdThreadLocal.set(userId);

        // 得到用户信息之后存入SecurityContext给后面的过滤器使用
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}
