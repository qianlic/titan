package com.cjwx.spark.server.config;

import com.cjwx.spark.server.capcha.Captcha;
import com.cjwx.spark.server.capcha.CaptchaHepler;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月03日 10:20
 */
@Data
@Component
@Configuration
@ConfigurationProperties("common.kaptcha")
public class CaptchaConfig {

    private String border;
    private String width;
    private String height;
    private String font;
    private String noise;
    private String chars;

    @Bean
    public DefaultKaptcha captchaProducer() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", border);
        properties.setProperty("kaptcha.image.width", width);
        properties.setProperty("kaptcha.image.height", height);
        properties.setProperty("kaptcha.textproducer.font.names", font);
        properties.setProperty("kaptcha.noise.impl", noise);
        properties.setProperty("kaptcha.textproducer.char.string", chars);
        Captcha config = new Captcha(properties);

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        CaptchaHepler.setCaptchaProducer(kaptcha);
        return kaptcha;
    }

}
