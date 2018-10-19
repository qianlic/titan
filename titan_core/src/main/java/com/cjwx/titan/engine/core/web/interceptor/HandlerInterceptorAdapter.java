package com.cjwx.titan.engine.core.web.interceptor;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.web.http.RequestHelper;
import com.cjwx.titan.engine.core.web.http.ResponseHelper;
import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.engine.util.StringUtils;
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
        if (url.startsWith("/system") && handlerMethod instanceof HandlerMethod) {
            String authHeader = request.getHeader(JwtHelper.AUTHORIZATION_KEY);
            if (StringUtils.isEmpty(authHeader)) {
                return this.response401();
            }
            JwtToken token = JwtHelper.parseToken(authHeader, RequestHelper.getClientIp());
            if (token == null) {
                return this.response401();
            } else if (!token.checkPromise(url)) {
                return this.response403();
            }
            HttpConstant.threadLocalModel.set(token);
        }
        return true;
    }

    private boolean response401() {
        Result result = new Result(false, "登录认证失败，请重新登录！");
        ResponseHelper.responseJson(HttpServletResponse.SC_UNAUTHORIZED, result);
        return false;
    }

    private boolean response403() {
        Result result = new Result(false, "无法访问资源，权限不足！");
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
