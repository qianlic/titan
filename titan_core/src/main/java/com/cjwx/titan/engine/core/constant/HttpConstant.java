package com.cjwx.titan.engine.core.constant;

import com.cjwx.titan.engine.reids.jwt.JwtToken;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: 常量类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class HttpConstant {

    public static final ThreadLocal<JwtToken> threadLocalModel = new ThreadLocal<>();
    public static final String SIGNING_KEY = "9af4d563dbc8cf2b08bd3391cd00c5f2";

    public static final String DEFAULT_FIX = "/system/*";

    public static final String FTP_UPLOAD = "/ftp/upload";
    public static final String FTP_DOWNLOAD = "/ftp/download";

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_DIGEST = "password";
    public static final String PARAM_VERIFYCODE = "verifycode";

    public static String VERSION;
    public static List<String> EXCLUSIONS;

    public void setVERSION(String version) {
        this.VERSION = version;
    }

    public void setEXCLUSIONS(List<String> exclusions) {
        this.EXCLUSIONS = exclusions;
    }

}
