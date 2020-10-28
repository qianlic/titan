package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.util.EndecryptUtils;
import com.cjwx.spark.engine.util.ExceptionUtils;
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

    @RestMethod("token")
    public SysUser token() {
        return TokenUtils.getCurrentUser();
    }

    @RestMethod("list")
    public ResultDTO<PageDTO<SysUserDTO>> list(@RequestBody SysUserDTO user) {
        try {
            return userService.getUserList(user, user.getStart(), user.getSize());
        } catch (Exception ex) {
            return ResultUtils.fail("操作失败!" + ex.getMessage());
        }
    }

    @RestMethod("download")
    public void download(@RequestBody SysUserDTO user) {
        try {
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
        } catch (Exception ex) {
            ExceptionUtils.throwError("下载失败!" + ex.getMessage());
        }
    }

    @RestMethod("create")
    public ResultDTO<Integer> create(@RequestBody SysUserDTO user) {
        try {
            String salt = EndecryptUtils.createSalt();
            String password = EndecryptUtils.md5Password("123456", salt);
            user.setSalt(salt);
            user.setPassword(password);
            user.setType("1");
            return userService.createUser(user);
        } catch (Exception ex) {
            return ResultUtils.fail("操作失败!" + ex.getMessage());
        }
    }

    @RestMethod("edit")
    public ResultDTO<Integer> edit(@RequestBody SysUserDTO user) {
        try {
            String salt = EndecryptUtils.createSalt();
            String password = EndecryptUtils.md5Password("123456", salt);
            user.setSalt(salt);
            user.setPassword(password);
            user.setType("1");
            return userService.updateUser(user);
        } catch (Exception ex) {
            return ResultUtils.fail("操作失败!" + ex.getMessage());
        }
    }

    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody List<Long> ids) {
        try {
            return userService.deleteUser(ids);
        } catch (Exception ex) {
            return ResultUtils.fail("操作失败!" + ex.getMessage());
        }
    }

    @RestMethod("status")
    public ResultDTO<Integer> status(@RequestBody JSONObject param) {
        try {
            List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
            boolean status = param.getBoolean("status");
            return userService.updateStatus(ids, status);
        } catch (Exception ex) {
            return ResultUtils.fail("操作失败!" + ex.getMessage());
        }
    }

    @RestMethod("password")
    public ResultDTO<Integer> password(@RequestBody JSONObject param) {
        try {
            List<Long> ids = param.getJSONArray("ids").toJavaList(Long.class);
            String password = param.getString("password");
            String salt = EndecryptUtils.createSalt();
            password = EndecryptUtils.md5Password(password, salt);
            return userService.updatePassword(ids, password, salt);
        } catch (Exception ex) {
            return ResultUtils.fail("操作失败!" + ex.getMessage());
        }
    }

}
