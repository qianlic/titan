package com.cjwx.spark.server.handler;

import com.cjwx.spark.server.entity.TomcatBean;
import com.cjwx.spark.server.helper.TomcatHelper;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import io.swagger.annotations.Api;

import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@Api(tags = "系统监控-TOMCAT")
@RestHandler("/tomcat/")
public class TomcatHandler {

    @RestMethod("list")
    public List<TomcatBean> list() {
        return TomcatHelper.findTomcatList();
    }

}
