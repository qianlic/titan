package com.cjwx.titan.engine.web.interceptor;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.web.http.RequestHelper;
import com.cjwx.titan.engine.web.http.ResponseHelper;
import com.cjwx.titan.engine.web.http.Result;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.engine.util.StringUtils;
import io.jsonwebtoken.ExpiredJwtException;
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
        String url = request.getServletPath();
        if (url.startsWith("/system") && handlerMethod instanceof HandlerMethod) {
            String authHeader = request.getHeader(JwtHelper.AUTHORIZATION_KEY);
            if (StringUtils.isEmpty(authHeader)) {
                return this.response401("登录未认证，请重新登录！");
            }
            try {
                JwtToken token = JwtHelper.parseToken(authHeader, RequestHelper.getClientIp());
                if (token == null) {
                    return this.response401("登录认证失败，请重新登录！");
                } else if (!token.checkPromise(url)) {
                    return this.response403("无法访问资源，权限不足！");
                }
                HttpConstant.threadLocalModel.set(token);
            } catch (ExpiredJwtException e) {
                return this.response401("凭据已过期，请重新认证！");
            }
        }
        return true;
    }

    private boolean response401(String message) {
        Result result = new Result(false, message);
        ResponseHelper.responseJson(HttpServletResponse.SC_UNAUTHORIZED, result);
        return false;
    }

    private boolean response403(String message) {
        Result result = new Result(false, message);
        ResponseHelper.responseJson(HttpServletResponse.SC_FORBIDDEN, result);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object controller, ModelAndView mv) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object controller, Exception ex) {
        HttpConstant.threadLocalModel.remove();
    }

}
