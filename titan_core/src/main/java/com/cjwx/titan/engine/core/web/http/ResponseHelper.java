package com.cjwx.titan.engine.core.web.http;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: Response响应工具类
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
public class ResponseHelper {

    private static final String CONTENT_TYPE = "application/json; charset=utf-8";

    public static void responseJson(ServletResponse response, int status, Result date) throws IOException {
        responseJson((HttpServletResponse) response, status, date);
    }

    public static void responseJson(HttpServletResponse response, int status, Result date) throws IOException {
        response.setStatus(status);
        responseJson(response, date);
    }

    public static void responseJson(ServletResponse response, Result date) throws IOException {
        response.setContentType(CONTENT_TYPE);
        String rspString = JSON.toJSONString(date);
        response.getWriter().write(rspString);
    }

    public static void initRequest200(HttpServletResponse response) {
        initRequestStatus(response, HttpServletResponse.SC_OK);
    }

    public static void initRequestStatus(HttpServletResponse response, int status) {
        response.setContentType(CONTENT_TYPE);
        response.setStatus(status);
    }

}
