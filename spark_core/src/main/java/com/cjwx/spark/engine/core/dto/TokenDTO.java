package com.cjwx.spark.engine.core.dto;

import com.cjwx.spark.engine.core.constant.AppConstant;
import com.cjwx.spark.engine.util.StringUtils;
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
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -9017410322938026917L;

    private String tokenId;
    private String host;

    private List<Pattern> promise;

    public boolean checkPromise(String url) {
        if (AppConstant.EXCLUSIONS.contains(url)) {
            return true;
        } else if (this.promise == null || StringUtils.isEmpty(url)) {
            return false;
        }
        return this.getPromise().stream().anyMatch(s -> s.matcher(url).matches());
    }

}
