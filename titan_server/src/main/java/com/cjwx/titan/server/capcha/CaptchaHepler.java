package com.cjwx.titan.server.capcha;

import com.cjwx.titan.engine.reids.util.RedisUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

    public static void drawVerifyCode(HttpServletResponse response, String key) {
        ServletOutputStream out = null;
        try {
            // 设置 session
            String capText = captchaProducer.createText();
            RedisUtils.set(key, capText, 60 * 1000);
            BufferedImage bi = captchaProducer.createImage(capText);
            // 设置清除浏览器的缓存
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/png");
            out = response.getOutputStream();
            // 输出验证码
            ImageIO.write(bi, "png", out);
        } catch (Exception e) {
            log.debug("文件上传异常：", e);
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
            }
        }
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
