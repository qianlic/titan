package com.cjwx.titan.monitor.handler;

import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.monitor.bean.TomcatBean;
import com.cjwx.titan.monitor.helper.TomcatHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description: 系统监控-TOMCAT
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@RestHandler
@RequestMapping(value = "/system/tomcat/", method = RequestMethod.POST)
public class TomcatHandler {

    @RequestMapping("list")
    public List<TomcatBean> list() {
        return TomcatHelper.findTomcatList();
    }

}
