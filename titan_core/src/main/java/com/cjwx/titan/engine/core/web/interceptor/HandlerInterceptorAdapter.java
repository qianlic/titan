package com.cjwx.titan.engine.core.web.interceptor;

import com.cjwx.titan.engine.core.constant.HttpConstant;
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
        if (handlerMethod instanceof HandlerMethod) {
            return true;
        }
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
