package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.core.exception.ServiceException;
import com.cjwx.titan.engine.core.web.http.RequestHelper;
import com.cjwx.titan.engine.core.web.http.Result;
import com.cjwx.titan.engine.core.web.http.ResultStatus;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.engine.util.EndecryptUtils;
import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.server.bean.SysRoleBean;
import com.cjwx.titan.server.bean.SysUserBean;
import com.cjwx.titan.server.capcha.CaptchaHepler;
import com.cjwx.titan.server.service.ResourceService;
import com.cjwx.titan.server.service.RoleService;
import com.cjwx.titan.server.service.UserService;
import com.cjwx.titan.server.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@Slf4j
@RestController
@RequestMapping(value = "/secure/")
public class SecureHandler {

    public static final String SYS_ACCOUNT = "admin";

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private ResourceService resourceService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody JSONObject object) {
        String s = object.getString("s");
        String verifycode = object.getString(HttpConstant.PARAM_VERIFYCODE);
        if (!CaptchaHepler.checkVerifyCode(s, verifycode)) {
            throw new ServiceException("验证码错误！");
        }
        String username = object.getString(HttpConstant.PARAM_USERNAME);
        SysUserBean user = userService.findUserByCode(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("账号[" + username + "]不存在！");
        } else if (Boolean.FALSE.equals(user.getStatus())) {
            throw new ServiceException("账号[" + username + "]锁定！");
        }
        String clientDigest = object.getString(HttpConstant.PARAM_DIGEST);
        String password = EndecryptUtils.md5Password(clientDigest, user.getSalt());
        if (!password.equalsIgnoreCase(user.getPassword())) {
            throw new ServiceException("用户名密码错误！");
        }
        if (SYS_ACCOUNT.equalsIgnoreCase(user.getUsername())) {
            user.setRoles(roleService.getRoleList());
            user.setResources(resourceService.getResourceList(true));
        } else {
            user.setRoles(roleService.findRolesByIds(user.getRoleids()));
            List<String> resourceIds = user.getRoles().stream()
                    .map(SysRoleBean::getResourceids)
                    .filter(StringUtils::isNotEmpty)
                    .flatMap(ids -> StringUtils.stringToList(ids).stream())
                    .distinct()
                    .collect(Collectors.toList());
            user.setResources(resourceService.findResourceByIds(resourceIds));
        }
        JwtToken token = new JwtToken();
        token.setHost(RequestHelper.getClientIp());
        token.setTokenId(StringUtils.randomUUID());
        token.setUser(user);
        return new Result(ResultStatus.STATUS_0, JwtHelper.createJWT(token));
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout(@RequestHeader(JwtHelper.AUTHORIZATION_KEY) String authHeader) {
        JwtHelper.removeToken(authHeader, RequestHelper.getClientIp());
        return "登录退出成功！";
    }

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public Result token() {
        return new Result(ResultStatus.STATUS_0,TokenUtils.getCurrentUser());
    }

    @RequestMapping(value = "captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("s");
        if (StringUtils.isNotEmpty(key)) {
            CaptchaHepler.drawVerifyCode(response, key);
        }
    }

}
