package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.core.dto.TokenDTO;
import com.cjwx.spark.engine.core.exception.ServiceException;
import com.cjwx.spark.engine.util.EndecryptUtils;
import com.cjwx.spark.engine.util.JwtTokenUtils;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.engine.web.http.RequestHelper;
import com.cjwx.spark.server.capcha.CaptchaHepler;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.service.ResourceService;
import com.cjwx.spark.server.service.RoleService;
import com.cjwx.spark.server.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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

    public static final String SYS_ACCOUNT = "0";

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private ResourceService resourceService;

    @RestMethod("login")
    public ResultDTO<String> login(@RequestBody JSONObject object) throws Exception {
        String s = object.getString("s");
        String verifyCode = object.getString(AppConstant.PARAM_VERIFYCODE);
        if (!CaptchaHepler.checkVerifyCode(s, verifyCode)) {
            throw new ServiceException("验证码错误！");
        }
        String username = object.getString(AppConstant.PARAM_USERNAME);
        ResultDTO<SysUserDTO> result = userService.findUserByCode(username);
        if (!result.isSuccess()) {
            throw new ServiceException("账号[" + username + "]不存在！");
        }
        SysUserDTO user = result.getData();
        if (Boolean.FALSE.equals(user.getStatus())) {
            throw new ServiceException("账号[" + username + "]锁定！");
        }
        String clientDigest = object.getString(AppConstant.PARAM_DIGEST);
        String password = EndecryptUtils.md5Password(clientDigest, user.getSalt());
        if (!password.equalsIgnoreCase(user.getPassword())) {
            throw new ServiceException("用户名密码错误！");
        }
        /*List<SysResource> resources;
        if (SYS_ACCOUNT.equals(user.getType())) {
            user.setRoles(roleService.getRoleList());
            resources = resourceService.getResourceList(true);
        } else {
            user.setRoles(roleService.findRolesByIds(user.getRoleids()));
            List<String> resourceIds = user.getRoles().stream()
                    .map(SysRoleBean::getResourceids)
                    .filter(StringUtils::isNotEmpty)
                    .flatMap(ids -> StringUtils.stringToList(ids).stream())
                    .distinct()
                    .collect(Collectors.toList());
            resources = resourceService.findResourceByIds(resourceIds);
        }
        user.setResources(resources);*/
        TokenDTO token = new TokenDTO();
        token.setHost(RequestHelper.getClientIp());
        token.setTokenId(StringUtils.randomUUID());
        /*token.setPromise(resources.stream()
                .filter(r -> "3".equals(r.getType()))
                .map(r -> {
                    String regex = r.getUrl().replace("*", "(.*)").replace("?", "(.{1})");
                    return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                }).collect(Collectors.toList()));*/
        //token.setUser(user);
        return ResultUtils.success(JwtTokenUtils.createJWT(token));
    }

    @RestMethod("logout")
    public ResultDTO<String> logout(@RequestHeader(JwtTokenUtils.AUTHORIZATION_KEY) String authHeader) {
        JwtTokenUtils.removeToken(authHeader, RequestHelper.getClientIp());
        return ResultUtils.success("登录退出成功！");
    }

    @RestMethod(value = "captcha", method = RequestMethod.GET)
    public void captcha(@RequestParam(name = "s") String key, HttpServletResponse response) throws Exception {
        if (StringUtils.isNotEmpty(key)) {
            ServletOutputStream out = null;
            try {
                // 设置清除浏览器的缓存
                response.setDateHeader("Expires", 0);
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                response.addHeader("Cache-Control", "post-check=0, pre-check=0");
                response.setHeader("Pragma", "no-cache");
                response.setContentType("image/png");
                out = response.getOutputStream();
                // 输出验证码
                ImageIO.write(CaptchaHepler.drawVerifyCode(key), "png", out);
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException ignored) {
                }
            }
        }
    }

}
