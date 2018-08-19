package com.cjwx.titan.shiro.filter;

import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.core.web.http.ResponseHelper;
import com.cjwx.titan.engine.core.web.http.ResultStatus;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.shiro.StatelessToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 无状态认证拦截器
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class StatelessAuthcFilter extends AccessControlFilter {

    /**
     * 是否允许访问，返回true表示允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) {
        try {
            String authHeader = ((HttpServletRequest) request).getHeader(JwtHelper.AUTHORIZATION_KEY);
            StatelessToken token = new StatelessToken(authHeader, request.getRemoteHost());
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String url = ((HttpServletRequest) request).getRequestURI();
            if (!url.equals("/system/user/token") && !subject.hasRole("manager")) {
                subject.checkPermission(url);
            }
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 表示访问拒绝时是否继续处理，返回true继续拦截器链执行
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Result date = new Result(ResultStatus.STATUS_1, "登录未认证！");
        ResponseHelper.responseJson(response, HttpServletResponse.SC_UNAUTHORIZED, date);
        return false;
    }

}
