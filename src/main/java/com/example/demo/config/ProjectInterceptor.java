package com.example.demo.config;

import com.example.demo.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hjkim27
 * @date 2024. 07. 07
 */
@Slf4j
public class ProjectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (LoginUtil.isLogin(request)) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
    }
}
