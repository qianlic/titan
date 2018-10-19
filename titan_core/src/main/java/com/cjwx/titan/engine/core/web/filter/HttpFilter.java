package com.cjwx.titan.engine.core.web.filter;

import com.cjwx.titan.engine.core.web.http.ResponseHelper;
import com.cjwx.titan.engine.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 预处理拦截器
 * @Author: qian li
 * @Date: 2018年03月29日 12:54
 */
@Slf4j
public class HttpFilter implements Filter {

    private String charset = StringUtils.DEFAULT_CHARSET;

    @Override
    public void init(FilterConfig filterConfig) {
        String charset = filterConfig.getServletContext().getInitParameter("charset");
        if (!StringUtils.isEmpty(charset)) {
            this.charset = charset;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", "*"); // 授权的源控制
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); // 允许请求的HTTP Method
        response.setHeader("Access-Control-Max-Age", "3600"); // 授权的时间
        response.setHeader("Access-Control-Allow-Credentials", "false"); // 控制是否开启与Ajax的Cookie提交方式
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization"); // 控制哪些header能发送真正的请求
        response.setHeader("content-type", "application/json;charset=UTF-8"); // 设置请求编码

        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {        //前端第一次参数访问直接过滤放回200
            ResponseHelper.initRequest200();
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        charset = StringUtils.DEFAULT_CHARSET;
    }

}
