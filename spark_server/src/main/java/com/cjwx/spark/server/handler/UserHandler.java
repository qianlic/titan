package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.util.EndecryptUtils;
import com.cjwx.spark.engine.util.ResultUtils;
import com.cjwx.spark.engine.util.file.ExcelUtils;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.service.UserService;
import com.cjwx.spark.server.util.TokenUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 系统管理-用户管理
 * @Author: qian li
 * @Date: 2020/10/28 16:55
 */
@Api(tags = "系统管理-用户管理")
@RestHandler("/user/")
public class UserHandler {

    @Resource
    private UserService userService;

    @RestMethod("create")
    public ResultDTO<Integer> create(@RequestBody SysUserDTO user) throws Exception {
        String salt = EndecryptUtils.createSalt();
        String password = EndecryptUtils.md5Password("123456", salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setType("1");
        return userService.createUser(user);
    }

    @RestMethod("edit")
    public ResultDTO<Integer> edit(@RequestBody SysUserDTO user) throws Exception {
        String salt = EndecryptUtils.createSalt();
        String password = EndecryptUtils.md5Password("123456", salt);
        user.setSalt(salt);
        user.setPassword(password);
        user.setType("1");
        return userService.updateUser(user);
    }

    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody List<Long> ids) {
        return userService.deleteUser(ids);
    }

    @RestMethod("list")
    public ResultDTO<PageDTO<SysUserDTO>> list(@RequestBody SysUserDTO user) throws Exception {
        return userService.getUserList(user, user.getStart(), user.getSize());
    }

    @RestMethod("status")
    public ResultDTO<Integer> status(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        boolean status = param.getBoolean("status");
        return userService.updateStatus(ids, status);
    }

    @RestMethod("password")
    public ResultDTO<Integer> password(@RequestBody JSONObject param) {
        List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
        String password = param.getString("password");
        String salt = EndecryptUtils.createSalt();
        password = EndecryptUtils.md5Password(password, salt);
        return userService.updatePassword(ids, password, salt);
    }

    @RestMethod("token")
    public ResultDTO<SysUserDTO> token() {
        return ResultUtils.success(TokenUtils.getCurrentUser());
    }

    @RestMethod("download")
    public void download(@RequestBody SysUserDTO user) throws Exception {
        ResultDTO<List<SysUserDTO>> result = userService.getUserList(user);
        if (result.isSuccess()) {
            List<SysUserDTO> users = result.getData();
            String[] title = {"用户编号", "用户名", "性别", "联系电话", "状态"};
            String[][] data = new String[users.size()][];
            for (int i = 0; i < users.size(); i++) {
                data[i] = new String[5];
                SysUserDTO u = users.get(i);
                data[i][0] = u.getUserCode();
                data[i][1] = u.getUserName();
                data[i][2] = u.getSex();
                data[i][3] = u.getMobile();
                data[i][4] = u.getStatus() ? "启用" : "禁用";
            }
            ExcelUtils.download(title, data);
        }
    }

}
