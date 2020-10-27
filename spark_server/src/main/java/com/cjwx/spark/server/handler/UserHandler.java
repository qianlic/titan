package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.server.util.TokenUtils;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.core.model.Model;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.util.EndecryptUtils;
import com.cjwx.spark.engine.util.file.ExcelUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年04月18日 10:54
 */
@Api(tags = "系统管理-用户管理")
@RestHandler("/user/")
public class UserHandler {

    @Resource
    private UserService userService;

    @RestMethod("token")
    public SysUser token() {
        return TokenUtils.getCurrentUser();
    }

    @RestMethod("list")
    public PageList<SysUser> list(@RequestBody Model<SysUser> model) {
        return userService.getUserList(model.getStart(), model.getSize(), model.getParams());
    }

    @RestMethod("download")
    public void download(@RequestBody SysUser model) {
        List<SysUser> users = userService.getUserList(model);
        String[] title = {"用户编号", "用户名", "性别", "联系电话", "状态"};
        String[][] data = new String[users.size()][];
        for (int i = 0; i < users.size(); i++) {
            data[i] = new String[5];
            SysUser user = users.get(i);
            data[i][0] = user.getUserCode();
            data[i][1] = user.getUserName();
            data[i][2] = user.getSex();
            data[i][3] = user.getMobile();
            data[i][4] = user.getStatus() ? "启用" : "禁用";
        }
        ExcelUtils.download(title, data);
    }

    @RestMethod("create")
    public void create(@RequestBody SysUser user) {
        String salt = EndecryptUtils.createSalt();
        String password = EndecryptUtils.md5Password("123456", salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setType("1");
        userService.createUser(user);
    }

    @RestMethod("edit")
    public void edit(@RequestBody SysUser user) {
        userService.updateUser(user);
    }

    @RestMethod("remove")
    public int remove(@RequestBody List<Long> ids) {
        return userService.deleteUser(ids);
    }

    @RestMethod("status")
    public int status(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        boolean status = param.getBoolean("status");
        return userService.updateStatus(ids, status);
    }

    @RestMethod("password")
    public int password(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        String password = param.getString("password");
        String salt = EndecryptUtils.createSalt();
        password = EndecryptUtils.md5Password(password, salt);
        return userService.updatePassword(ids, password, salt);
    }

}
