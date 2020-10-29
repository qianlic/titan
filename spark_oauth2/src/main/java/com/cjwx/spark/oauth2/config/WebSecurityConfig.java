package com.cjwx.spark.oauth2.config;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.JwtTokenUtils;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.web.http.RequestHelper;
import com.cjwx.spark.engine.web.http.ResponseHelper;
import com.cjwx.spark.oauth2.JsonAuthenticationFilter;
import com.cjwx.spark.oauth2.JsonAuthenticationProvider;
import com.cjwx.spark.oauth2.JwtAuthenticationFilter;
import com.cjwx.spark.oauth2.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 安全登录管理
 * @Author: qian li
 * @Date: 2020年09月03日 13:15
 */
@Configuration
@EnableWebSecurity
@Import(UserDetailsServiceImpl.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/eureka/**")
                .antMatchers(HttpMethod.GET, "/", "/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(jsonAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "*/*").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //允许跨域请求
                .cors().and()
                //CSRF攻击配置
                .csrf().disable()
                .formLogin().disable()
                //.httpBasic().and()
                //关闭默认登出
                .logout().logoutSuccessHandler(logoutHandler()).and()
                .exceptionHandling().authenticationEntryPoint(exceptionHandler()).and()
                .addFilterAfter(jsonAuthenticationFilter(), LogoutFilter.class)
                .addFilterAfter(jwtAuthenticationFilter(), LogoutFilter.class)
                .anonymous().disable()
                //设置无状态session
                .sessionManagement().disable()
                //禁用缓存
                .headers().cacheControl();
    }

    public JsonAuthenticationFilter jsonAuthenticationFilter() throws Exception {
        JsonAuthenticationFilter authFilter = new JsonAuthenticationFilter();
        authFilter.setAuthenticationManager(authenticationManagerBean());
        authFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        authFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return authFilter;
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            String principal = authentication.getPrincipal().toString();
            String clientIp = RequestHelper.getClientIp();
            JSONObject token = new JSONObject();
            token.put("token", JwtTokenUtils.createJWT(principal, clientIp).compact());
            responseWriter(response, ResultUtils.success(token));
        };
    }

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) ->
                responseWriter(response, ResultUtils.fail(exception.getMessage()));
    }

    public LogoutSuccessHandler logoutHandler() {
        return (request, response, authentication) ->
                responseWriter(response, ResultUtils.success());
    }

    public AuthenticationEntryPoint exceptionHandler() {
        return (request, response, exception) -> {
            String errorMsg = exception.getMessage();
            if (exception instanceof AuthenticationCredentialsNotFoundException) {
                errorMsg = "未配置身份认证信息";
            }
            responseWriter(response, ResultUtils.fail(errorMsg));
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected AuthenticationProvider jsonAuthenticationProvider() {
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        JsonAuthenticationProvider jsonProvider = new JsonAuthenticationProvider();
        jsonProvider.setUserDetailsService(userDetailsService);
        return jsonProvider;
    }

    private <T> void responseWriter(HttpServletResponse response, ResultDTO<T> result) {
        ResponseHelper.writeJson(response, result);
    }

}
