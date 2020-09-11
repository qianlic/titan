package com.cjwx.spark.engine.web.http;

import com.alibaba.fastjson.JSON;
import com.cjwx.spark.engine.core.constant.HttpConstant;
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
        response.setContentType(HttpConstant.DEFAULT_MEDIA_TYPE);
        response.setStatus(status);
        return response;
    }

}
