package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.EndecryptUtils;
import com.cjwx.spark.engine.util.JwtTokenUtils;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.engine.web.http.RequestHelper;
import com.cjwx.spark.server.capcha.CaptchaHepler;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.dto.UserLoginDTO;
import com.cjwx.spark.server.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@Slf4j
@Api(tags = "权限认证")
@RestHandler("/secure/")
public class SecureHandler {

    @Resource
    private UserService userService;

    @RestMethod("login")
    public ResultDTO<UserLoginDTO> login(@RequestBody JSONObject object) throws Exception {
        String s = object.getString("s");
        String verifyCode = object.getString(AppConstant.PARAM_VERIFYCODE);
        if (!CaptchaHepler.checkVerifyCode(s, verifyCode)) {
            return ResultUtils.fail("验证码错误！");
        }
        String username = object.getString(AppConstant.PARAM_USERNAME);
        ResultDTO<SysUserDTO> result = userService.findUserByCode(username);
        if (!result.isSuccess()) {
            return ResultUtils.fail("账号[" + username + "]不存在！");
        }
        SysUserDTO user = result.getData();
        if (!user.getStatus()) {
            return ResultUtils.fail("账号[" + username + "]锁定！");
        }
        String clientDigest = object.getString(AppConstant.PARAM_DIGEST);
        String password = EndecryptUtils.md5Password(clientDigest, user.getSalt());
        if (!password.equals(user.getPassword())) {
            return ResultUtils.fail("用户名密码错误！");
        }
        UserLoginDTO logInfo = new UserLoginDTO();
        BeanUtils.copyProperties(user, logInfo);
        logInfo.setToken(JwtTokenUtils.createJWT(logInfo));
        return ResultUtils.success(logInfo);
    }

    @RestMethod("logout")
    public ResultDTO<String> logout(@RequestHeader(JwtTokenUtils.AUTHORIZATION_KEY) String authHeader) {
        JwtTokenUtils.removeToken(authHeader, RequestHelper.getClientIp());
        return ResultUtils.success("登录退出成功！");
    }

    @RestMethod(value = "captcha", method = RequestMethod.GET)
    public void captcha(@RequestParam(name = "s") String key, HttpServletResponse response) throws Exception {
        if (StringUtils.isNotEmpty(key)) {
            try (ServletOutputStream out = response.getOutputStream()) {
                // 设置清除浏览器的缓存
                response.setDateHeader("Expires", 0);
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                response.addHeader("Cache-Control", "post-check=0, pre-check=0");
                response.setHeader("Pragma", "no-cache");
                response.setContentType("image/png");
                // 输出验证码
                ImageIO.write(CaptchaHepler.drawVerifyCode(key), "png", out);
            }
        }
    }

}
