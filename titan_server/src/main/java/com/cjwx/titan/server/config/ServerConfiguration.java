package com.cjwx.titan.server.config;

import com.cjwx.titan.engine.config.ApplicationConfiguration;
import com.cjwx.titan.engine.config.WebMvcConfiguration;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.server.bean.SysResourceBean;
import com.cjwx.titan.server.service.ResourceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月03日 10:20
 */
@Data
@Configuration
@Import({ApplicationConfiguration.class, WebMvcConfiguration.class})
@ImportResource("classpath:spring-server.xml")
public class ServerConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private List<RequestMappingHandlerMapping> handlerMappings;
    @Autowired
    private ResourceService resourceService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            List<String> urls = new ArrayList<>();
            Map<String, Long> allMap = new HashMap<>();
            resourceService.getResourceList().forEach(r -> {
                urls.add(r.getUrl());
                allMap.put(r.getResourcecode(), r.getId());
            });
            List<SysResourceBean> add = handlerMappings.stream()
                    .map(RequestMappingHandlerMapping::getHandlerMethods)
                    .flatMap(h -> h.keySet().stream())
                    .flatMap(h -> h.getPatternsCondition().getPatterns().stream())
                    .map(url -> url.replace("//", "/").toLowerCase())
                    .distinct()
                    .filter(url -> url.startsWith("/system")
                            && !HttpConstant.EXCLUSIONS.contains(url)
                            && !urls.contains(url))
                    .map(url -> new UrlParser(allMap, url))
                    .filter(UrlParser::isPass)
                    .map(UrlParser::getResource)
                    .collect(Collectors.toList());
            if (add.size() > 0) {
                resourceService.createResource(add);
            }
        }
    }

    @Data
    class UrlParser {
        private String url;
        private Long parentid;
        private boolean pass = false;
        private String key;
        private String code;
        private String[] tags;

        public UrlParser(Map<String, Long> allMap, String url) {
            this.url = url;
            this.tags = url.split("/");
            int l = this.tags.length;
            if (l > 3) {
                this.key = this.tags[l - 2];
                this.parentid = allMap.get(key.toLowerCase());
                if (this.parentid != null) {
                    this.pass = true;
                    this.code = (key + "_" + this.tags[l - 1]).toUpperCase();
                }
            }
        }

        public SysResourceBean getResource() {
            SysResourceBean resource = new SysResourceBean();
            resource.setParentid(this.parentid);
            resource.setUrl(this.url);
            resource.setType("3");
            resource.setLevel(2);
            resource.setResourcecode(this.code);
            resource.setResourcename(this.code);
            resource.setStatus(true);
            return resource;
        }
    }
}
