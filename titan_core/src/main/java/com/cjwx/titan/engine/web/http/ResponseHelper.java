package com.cjwx.titan.engine.web.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: Response响应工具类
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
@Slf4j
public class ResponseHelper {

    private static final String CONTENT_TYPE = "application/json; charset=utf-8";

    public static HttpServletResponse getResponse() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) attributes).getResponse();
    }


    public static void responseJson(Result date) {
        responseJson(HttpServletResponse.SC_OK, date);
    }


    public static void responseJson(int status, Result date) {
        try {
            HttpServletResponse response = initRequestStatus(status);
            String rspString = JSON.toJSONString(date);
            response.getWriter().write(rspString);
        } catch (IOException e) {
            log.error("返回信息异常", e);
        }
    }

    public static HttpServletResponse initRequest200() {
        return initRequestStatus(HttpServletResponse.SC_OK);
    }

    public static HttpServletResponse initRequestStatus(int status) {
        HttpServletResponse response = getResponse();
        response.setContentType(CONTENT_TYPE);
        response.setStatus(status);
        return response;
    }

}
