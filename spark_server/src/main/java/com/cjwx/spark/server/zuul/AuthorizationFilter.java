package com.cjwx.spark.server.zuul;

import com.alibaba.fastjson.JSON;
import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.TokenDTO;
import com.cjwx.spark.engine.util.JwtTokenUtils;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.http.RequestHelper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录认证拦截器
 * @Author: qian li
 * @Date: 2018年11月05日 15:12
 */
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
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
        String url = request.getServletPath().replace("//", "/").toLowerCase();
        if (!url.contains(DocumentationProvider.API_DOC)) {
            String authHeader = request.getHeader(JwtTokenUtils.AUTHORIZATION_KEY);
            if (StringUtils.isEmpty(authHeader)) {
                return this.response401(ctx, "登录认证失败，请重新登录！");
            }
            try {
                TokenDTO token = JwtTokenUtils.parseToken(authHeader, RequestHelper.getClientIp());
                if (token == null) {
                    return this.response401(ctx, "登录认证失败，请重新登录！");
                } else if (!token.checkPromise(url)) {
                    return this.response403(ctx, "无法访问资源，权限不足！");
                }
                String user = StringUtils.encodeURLEncoder(JSON.toJSONString(token));
                ctx.addZuulRequestHeader("CURRENT_USER", user);
            } catch (ExpiredJwtException e) {
                return this.response401(ctx, "凭据已过期，请重新认证！");
            }
        }
        return null;
    }

    private Object response401(RequestContext ctx, String message) {
        return this.writeJson(ctx, HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    private Object response403(RequestContext ctx, String message) {
        return this.writeJson(ctx, HttpServletResponse.SC_FORBIDDEN, message);
    }

    private Object writeJson(RequestContext ctx, int status, String message) {
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(status);
        ctx.getResponse().setContentType(AppConstant.DEFAULT_MEDIA_TYPE);
        ctx.setResponseBody(JSON.toJSONString(ResultUtils.fail(message)));
        return null;
    }

}
