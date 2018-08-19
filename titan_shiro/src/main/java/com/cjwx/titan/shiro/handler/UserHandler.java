package com.cjwx.titan.shiro.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.engine.core.web.http.RequestHelper;
import com.cjwx.titan.engine.reids.jwt.JwtHelper;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.engine.util.DateUtils;
import com.cjwx.titan.engine.util.EndecryptUtils;
import com.cjwx.titan.shiro.bean.SysUserBean;
import com.cjwx.titan.shiro.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 系统管理-用户管理
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@RestHandler
@RequestMapping(value = "/system/user/", method = RequestMethod.POST)
public class UserHandler {

    @Resource
    private UserService userService;

    @RequestMapping("token")
    public JwtToken token(@RequestHeader(JwtHelper.AUTHORIZATION_KEY) String authHeader) {
        JwtToken token = JwtHelper.parseToken(authHeader, RequestHelper.getClientIp());
        Map<String, Object> edit = new HashMap<>();
        edit.put("lastlogintime", DateUtils.now());
        userService.updateUser(token.getInteger("userid"), edit);
        return token;
    }

    @RequestMapping("list")
    public PageList<SysUserBean> list(@RequestBody Model model) {
        return userService.getUserList(model.getStart(), model.getSize(), model.getParams());
    }

    @RequestMapping("create")
    public void create(@RequestBody SysUserBean user) {
        String salt = EndecryptUtils.createSalt();
        String password = EndecryptUtils.md5Password("123456", salt);
        user.setSalt(salt);
        user.setPassword(password);
        userService.createUser(user);
    }

    @RequestMapping("edit")
    public int edit(@RequestBody Model model) {
        return userService.updateUser(model.getId(), model.getParams());
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return userService.deleteUser(ids);
    }

    @RequestMapping("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("state");
        return userService.updateStatus(ids, status);
    }

    @RequestMapping("password")
    public int password(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        String password = model.getString("password");
        String salt = EndecryptUtils.createSalt();
        password = EndecryptUtils.md5Password(password, salt);
        return userService.updatePassword(ids, password, salt);
    }

}
