package com.cjwx.titan.server.config;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月03日 10:20
 */
@Data
@Configuration
@ConfigurationProperties("common.wxmp")
public class WxmpConfiguration {

    private String appId;
    private String secret;
    private String token;
    private String aesKey;

    @Bean
    public WxMpInMemoryConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appId);
        config.setSecret(secret);
        config.setToken(token);
        config.setAesKey(aesKey);
        return config;
    }

    @Bean
    public WxMpServiceImpl wxMpService(WxMpInMemoryConfigStorage wxMpConfigStorage) {
        WxMpServiceImpl service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(wxMpConfigStorage);
        return service;
    }

    @Bean
    public WxMpMessageRouter wxMpMessageRouter(WxMpServiceImpl wxMpService) {
        return new WxMpMessageRouter(wxMpService);
    }

}
