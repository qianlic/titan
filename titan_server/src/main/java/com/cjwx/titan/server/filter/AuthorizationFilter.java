package com.cjwx.titan.server.filter;

import com.alibaba.fastjson.JSON;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.engine.web.http.RequestHelper;
import com.cjwx.titan.engine.web.http.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 登录认证拦截器
 * @Author: qian li
 * @Date: 2018年11月05日 15:12
 */
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {

    //定义filter的类型，有pre、route、post、error四种
    private static final String FILTER_TYPE = "pre";
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;//表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getServletPath();
        String authHeader = request.getHeader(JwtHelper.AUTHORIZATION_KEY);
        if (StringUtils.isEmpty(authHeader)) {
            return this.response401(ctx, "登录未认证，请重新登录！");
        }
        try {
            JwtToken token = JwtHelper.parseToken(authHeader, RequestHelper.getClientIp());
            if (token == null) {
                return this.response401(ctx, "登录认证失败，请重新登录！");
            } else if (!token.checkPromise(url)) {
                return this.response403(ctx, "无法访问资源，权限不足！");
            }
            String user = StringUtils.encodeURLEncoder(JSON.toJSONString(token.getUser()));
            ctx.addZuulRequestHeader("CURRENT_USER", user);
        } catch (ExpiredJwtException e) {
            return this.response401(ctx, "凭据已过期，请重新认证！");
        }
        return null;
    }

    private Object response401(RequestContext ctx, String message) {
        return this.responseJson(ctx, HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    private Object response403(RequestContext ctx, String message) {
        return this.responseJson(ctx, HttpServletResponse.SC_FORBIDDEN, message);
    }

    private Object responseJson(RequestContext ctx, int status, String message) {
        try {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(status);
            HttpServletResponse response = ctx.getResponse();
            response.setContentType(CONTENT_TYPE);
            String rspString = JSON.toJSONString(new Result(false, message));
            response.getWriter().write(rspString);
        } catch (IOException e) {
            log.error("返回信息异常", e);
        }
        return null;
    }

}
