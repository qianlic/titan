package com.cjwx.titan.engine.core.web.http;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.titan.engine.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description: Request请求工具类
 * @Author: qian li
 * @Date: 2018年03月29日 13:18
 */
public class RequestHelper {

    public static String getClientIp() {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {
            return "UnknownHost";
        }
    }

    public static JSONObject requestJson(ServletRequest request) throws IOException {
        String jsonString = requestString(request);
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return JSONObject.parseObject(jsonString);
    }

    public static String requestString(ServletRequest request) throws IOException {
        InputStreamReader ir = new InputStreamReader(request.getInputStream(), StringUtils.DEFAULT_CHARSET);
        BufferedReader br = new BufferedReader(ir);
        StringBuffer sb = new StringBuffer();
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        return sb.toString();
    }

}
