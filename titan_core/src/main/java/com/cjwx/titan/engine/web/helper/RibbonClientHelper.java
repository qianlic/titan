package com.cjwx.titan.engine.web.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    public static <T> T getResult(String serviceId, String url, Class<T> responseType) {
        return restTemplate.getForEntity("http://" + serviceId + "/" + url, responseType).getBody();
    }

    public static <T> List<T> getResult(String url, Class<T> responseType) {
        return getServiceIds().stream()
                .map(s -> getResult(s, url, responseType))
                .collect(Collectors.toList());
    }

    public static List<String> getServiceIds() {
        return discoveryClient.getServices();
    }


}
