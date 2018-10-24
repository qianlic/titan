package com.cjwx.titan.engine.reids.jwt;

import com.cjwx.titan.engine.core.base.bean.UserBean;
import com.cjwx.titan.engine.core.constant.HttpConstant;
import com.cjwx.titan.engine.util.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: JWT TOKEN
 * @Author: qian li
 * @Date: 2018年03月31日 16:20
 */
@Data
public class JwtToken implements Serializable {

    private static final long serialVersionUID = -9017410322938026917L;

    private String tokenId;
    private String host;

    private UserBean user;
    private List<Pattern> promise;

    public boolean checkPromise(String url) {
        if (HttpConstant.EXCLUSIONS.contains(url)) {
            return true;
        } else if (this.promise == null || StringUtils.isEmpty(url)) {
            return false;
        }
        return this.getPromise().stream().anyMatch(s -> s.matcher(url).matches());
    }

}
