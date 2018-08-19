package com.cjwx.titan.shiro.filter;

import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.core.web.http.ResponseHelper;
import com.cjwx.titan.engine.core.web.http.ResultStatus;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 无状态退出登录拦截器
 * @Author: qian li
 * @Date: 2018年03月29日 12:54
 */
public class StatelessLogoutFilter extends AdviceFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String authHeader = ((HttpServletRequest) request).getHeader(JwtHelper.AUTHORIZATION_KEY);
        JwtHelper.removeToken(authHeader, request.getRemoteHost());

        Result date = new Result(ResultStatus.STATUS_0, "登录退出成功！");
        ResponseHelper.responseJson(response, HttpServletResponse.SC_UNAUTHORIZED, date);
        return false;
    }

}
