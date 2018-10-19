package com.cjwx.titan.engine.core.web.interceptor;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.web.http.RequestHelper;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Handler拦截器
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
public class HandlerInterceptorAdapter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handlerMethod) {
        String url = request.getRequestURI();
        if ((url.startsWith("/system") || "/secure/token".equals(url)) && handlerMethod instanceof HandlerMethod) {
            String authHeader = request.getHeader(JwtHelper.AUTHORIZATION_KEY);
            JwtToken token = JwtHelper.parseToken(authHeader, RequestHelper.getClientIp());
            if (token == null) {
                return false;
            }
            HttpConstant.threadLocalModel.set(token);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView mv) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object controller, Exception ex) {
        HttpConstant.threadLocalModel.remove();
    }

}
