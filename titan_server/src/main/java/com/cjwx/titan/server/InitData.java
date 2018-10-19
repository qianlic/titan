package com.cjwx.titan.server;

import com.cjwx.titan.server.bean.SysResourceBean;
import com.cjwx.titan.server.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年10月16日 15:09
 */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    private ResourceService resourceService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
            List<SysResourceBean> add = new ArrayList<>();
            List<String> urls = new ArrayList<>();
            Map<String, Long> allMap = new HashMap<>();
            resourceService.getResourceList().forEach(r -> {
                urls.add(r.getUrl());
                allMap.put(r.getResourcecode(), r.getId());
            });
            handlerMethods.keySet().forEach(handler ->
                    handler.getPatternsCondition().getPatterns().forEach(url -> {
                        url = url.replace("//", "/").toLowerCase();
                        String[] s = url.split("/");
                        int l = s.length;
                        if (url.startsWith("/system") && !urls.contains(url) && l > 3) {
                            SysResourceBean resource = new SysResourceBean();
                            String key = s[l - 2];
                            Long parentid = allMap.get(key.toLowerCase());
                            if (parentid != null) {
                                resource.setParentid(parentid);
                                String code = key + "_" + s[l - 1];
                                resource.setUrl(url);
                                resource.setType("3");
                                resource.setLevel(2);
                                resource.setResourcecode(code.toUpperCase());
                                resource.setResourcename(code.toUpperCase());
                                resource.setStatus(true);
                                add.add(resource);
                            }
                        }
                    })
            );
            if (add.size() > 0) {
                resourceService.createResource(add);
            }
        }
    }
}