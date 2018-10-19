package com.cjwx.titan.server.capcha;

import com.cjwx.titan.engine.reids.util.RedisUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年10月18日 16:25
 */
@Slf4j
public class CaptchaHepler {

    private static DefaultKaptcha captchaProducer;

    @Autowired
    public void setCaptchaProducer(DefaultKaptcha captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    public static BufferedImage drawVerifyCode(String key) {
        // 设置 session
        String capText = captchaProducer.createText();
        RedisUtils.set(key, capText, 60 * 1000);
        return captchaProducer.createImage(capText);
    }

    /**
     * 验证码校验
     */
    public static boolean checkVerifyCode(String key, String code) {
        if (!RedisUtils.exists(key)) {
            return false;
        }
        String value = RedisUtils.get(key).toString();
        RedisUtils.remove(key);
        return StringUtils.isNotEmpty(code) && code.equalsIgnoreCase(value);
    }

}
