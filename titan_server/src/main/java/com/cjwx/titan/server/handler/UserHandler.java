package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.base.bean.UserBean;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.engine.util.EndecryptUtils;
import com.cjwx.titan.server.bean.SysUserBean;
import com.cjwx.titan.server.service.UserService;
import com.cjwx.titan.server.util.TokenUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@RestHandler("系统管理-用户管理")
@RequestMapping(value = "/system/user/", method = RequestMethod.POST)
public class UserHandler {

    @Resource
    private UserService userService;

    @RequestMapping(value = "token")
    public UserBean token() {
        return TokenUtils.getCurrentUser();
    }

    @RequestMapping("list")
    public PageList<SysUserBean> list(@RequestBody Model model) {
        return userService.getUserList(model.getStart(), model.getSize(), model.getParams(SysUserBean.class));
    }

    @RequestMapping("create")
    public void create(@RequestBody SysUserBean user) {
        String salt = EndecryptUtils.createSalt();
        String password = EndecryptUtils.md5Password("123456", salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setType("1");
        userService.createUser(user);
    }

    @RequestMapping("edit")
    public int edit(@RequestBody Model model) {
        return userService.updateUser(model.getId(), model.getParams(SysUserBean.class));
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return userService.deleteUser(ids);
    }

    @RequestMapping("status")
    public int status(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        boolean status = model.getBoolean("status");
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