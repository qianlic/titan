package com.cjwx.titan.engine.web.helper;

import com.alibaba.fastjson.JSON;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月06日 20:02
 */

@Slf4j
@Component
public class RibbonClientHelper {

    private static DiscoveryClient discoveryClient;
    private static RestTemplate restTemplate;

    @Autowired
    public void setDiscoveryClient(DiscoveryClient client) {
        discoveryClient = client;
    }

    @Autowired
    public void setRestTemplate(RestTemplate rest) {
        restTemplate = rest;
    }

    public static <T> T exchange(String url, HttpMethod method, Object params, Class<T> responseType) {
        return restTemplate.exchange(url, method, getRequestEntity(params), responseType).getBody();
    }

    public static <T> T doPost(String serviceId, String url, Object params, Class<T> responseType) {
        return exchange("http://" + serviceId + "/" + url, HttpMethod.POST, params, responseType);
    }

    public static <T> T doPost(String serviceId, String url, Class<T> responseType) {
        return doPost(serviceId, url, null, responseType);
    }

    public static <T> List<T> doPost(String url, Class<T> responseType) {
        return getServiceIds().stream()
                .map(s -> doPost(s, url, responseType))
                .collect(Collectors.toList());
    }

    public static List<String> getServiceIds() {
        return discoveryClient.getServices();
    }

    public static HttpEntity<String> getRequestEntity(Object params) {
        return new HttpEntity<>(JSON.toJSONString(params), getDefaultHeaders());
    }

    public static HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(HttpConstant.DEFAULT_MEDIA_TYPE));
        return headers;
    }

}
