package com.cjwx.titan.server.util;

import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.reids.jwt.JwtToken;
import com.cjwx.titan.server.bean.SysUserBean;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月27日 19:49
 */
public class TokenUtils {

    public static SysUserBean getCurrentUser() {
        JwtToken token = HttpConstant.threadLocalModel.get();
        if (token == null) {
            return null;
        }
        return (SysUserBean) token.getUser();
    }

}
