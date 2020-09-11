package com.cjwx.spark.server.capcha;

import com.cjwx.spark.engine.reids.util.RedisUtils;
import com.cjwx.spark.engine.util.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年10月18日 16:25
 */
@Slf4j
public class CaptchaHepler {

    private static DefaultKaptcha captchaProducer;

    public static void setCaptchaProducer(DefaultKaptcha producer) {
        captchaProducer = producer;
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
