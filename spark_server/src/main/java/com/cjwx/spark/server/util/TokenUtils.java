package com.cjwx.spark.server.util;

import com.alibaba.fastjson.JSON;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.util.StringUtils;
import com.cjwx.spark.engine.web.http.RequestHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月27日 19:49
 */
public class TokenUtils {

    public static SysUser getCurrentUser() {
        HttpServletRequest request = RequestHelper.getRequest();
        String user = StringUtils.decodeURLEncoder(request.getHeader("CURRENT_USER"));
        return JSON.parseObject(user, SysUser.class);
    }

}
