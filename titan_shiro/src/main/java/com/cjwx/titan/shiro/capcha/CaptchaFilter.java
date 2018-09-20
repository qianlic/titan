package com.cjwx.titan.shiro.capcha;

import com.cjwx.titan.engine.reids.util.RedisUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.Data;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Data
public class CaptchaFilter extends OncePerRequestFilter {

    private DefaultKaptcha captchaProducer;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        String key = request.getParameter("s");
        if (StringUtils.isNotEmpty(key)) {
            // 设置清除浏览器的缓存
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/png");
            // 设置 session
            String capText = captchaProducer.createText();
            RedisUtils.set(key, capText, 60 * 1000);
            // 输出验证码
            BufferedImage bi = captchaProducer.createImage(capText);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bi, "png", out);
            out.flush();
            out.close();
        }
    }

}
