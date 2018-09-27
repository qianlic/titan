package com.cjwx.titan.shiro.util;

import com.cjwx.titan.engine.util.ObjectUtils;
import com.cjwx.titan.shiro.StatelessToken;
import org.apache.shiro.SecurityUtils;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年09月27日 19:49
 */
public class TokenUtils {

    public static StatelessToken getCurrentToken() {
        Object token = SecurityUtils.getSubject().getPrincipal();
        if (ObjectUtils.isEmpty(token) || !(token instanceof StatelessToken)) {
            return null;
        }
        return (StatelessToken) token;
    }

}
