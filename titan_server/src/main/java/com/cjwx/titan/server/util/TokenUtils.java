package com.cjwx.titan.server.util;

import com.alibaba.fastjson.JSON;
import com.cjwx.titan.engine.util.StringUtils;
import com.cjwx.titan.engine.web.http.RequestHelper;
import com.cjwx.titan.server.bean.SysUserBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月27日 19:49
 */
public class TokenUtils {

    public static SysUserBean getCurrentUser() {
        HttpServletRequest request = RequestHelper.getRequest();
        String user = StringUtils.decodeURLEncoder(request.getHeader("CURRENT_USER"));
        return JSON.parseObject(user, SysUserBean.class);
    }

}
