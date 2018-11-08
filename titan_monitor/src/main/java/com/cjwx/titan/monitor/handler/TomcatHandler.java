package com.cjwx.titan.monitor.handler;

import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.monitor.bean.TomcatBean;
import com.cjwx.titan.monitor.helper.TomcatHelper;
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
