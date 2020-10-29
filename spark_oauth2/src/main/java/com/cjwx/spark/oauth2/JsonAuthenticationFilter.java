package com.cjwx.spark.oauth2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.constant.AppConstant;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020年09月04日} 10:37
 */
public class JsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String HTTP_METHOD = "POST";

    public JsonAuthenticationFilter() {
        super(new AntPathRequestMatcher(AppConstant.LOGIN_URL, HTTP_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication contentType not supported: " + request.getContentType());
        }
        try (InputStream is = request.getInputStream()) {
            JSONObject json = JSON.parseObject(is, JSONObject.class);
            String principal = json.getString(AppConstant.PARAM_USERNAME);
            String credentials = json.getString(AppConstant.PARAM_DIGEST);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

}
