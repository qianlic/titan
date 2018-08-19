package com.cjwx.titan.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.web.http.RequestHelper;
import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.core.web.http.ResponseHelper;
import com.cjwx.titan.engine.core.web.http.ResultStatus;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.shiro.StatelessToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 无状态登录拦截器
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Slf4j
public class StatelessLoginFilter extends AdviceFilter {


    /**
     * 表示是否继续处理，返回true继续拦截器链执行
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        int responseStatus = HttpServletResponse.SC_UNAUTHORIZED;
        Result date;
        try {
            JSONObject jsonObject = RequestHelper.requestJson(request);
            String username = jsonObject.getString(HttpConstant.PARAM_USERNAME);
            String clientDigest = jsonObject.getString(HttpConstant.PARAM_DIGEST);
            //4、生成无状态Token
            StatelessToken token = new StatelessToken(username, clientDigest, request.getRemoteHost());
            //5、委托给Realm进行登录
            SecurityUtils.getSubject().login(token);
            String authorization = JwtHelper.createJWT(token);
            date = new Result(ResultStatus.STATUS_0, authorization);
            responseStatus = HttpServletResponse.SC_OK;
        } catch (ExcessiveAttemptsException eae) {
            log.debug(eae.getMessage(), eae);
            date = new Result(ResultStatus.STATUS_1, "登录失败次数较多，请稍后再试！");
        } catch (LockedAccountException lae) {
            log.debug(lae.getMessage(), lae);
            date = new Result(ResultStatus.STATUS_1, lae.getMessage());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            date = new Result(ResultStatus.STATUS_1, "用户名密码错误！");
        }
        ResponseHelper.responseJson(response, responseStatus, date);
        return false;
    }

}
