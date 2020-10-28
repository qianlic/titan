package com.cjwx.spark.engine.core.constant;

import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description: 常量类
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
public class AppConstant {

    public static final String SIGNING_KEY = "9af4d563dbc8cf2b08bd3391cd00c5f2";

    public static final String LOGIN_URL = "/login";

    public static final String FTP_UPLOAD = "/ftp/upload";
    public static final String FTP_DOWNLOAD = "/ftp/download";

    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_DIGEST = "password";
    public static final String PARAM_VERIFYCODE = "verifycode";

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;

    public static final String VERSION = "0.0.1";
    public static List<String> EXCLUSIONS = Collections.singletonList("/system/user/token");

}
