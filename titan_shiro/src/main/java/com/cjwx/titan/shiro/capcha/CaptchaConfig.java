package com.cjwx.titan.shiro.capcha;

import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Properties;
import java.util.Random;

/**
 * @Description: 随机颜色
 * @Author: qian li
 * @Date: 2018年09月19日 17:24
 */
@Slf4j
public class CaptchaConfig extends Config {

    public CaptchaConfig(Properties properties) {
        super(properties);
    }

    public Color getTextProducerFontColor() {
        return randomRGB(150);
    }

    public Color getNoiseColor() {
        return randomRGB(120);
    }

    public Color getBackgroundColorFrom() {
        return randomRGB(200, 55);
    }

    public Color getBackgroundColorTo() {
        return randomRGB(200, 55);
    }

    private Color randomRGB(int limit) {
        return randomRGB(0, limit);
    }

    private Color randomRGB(int start, int limit) {
        Random random = new Random();
        int r = start + random.nextInt(limit);
        int g = start + random.nextInt(limit);
        int b = start + random.nextInt(limit);
        return new Color(r, g, b);
    }

}
