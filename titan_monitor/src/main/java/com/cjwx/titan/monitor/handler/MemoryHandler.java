package com.cjwx.titan.monitor.handler;

import com.cjwx.titan.monitor.bean.MemoryBean;
import com.cjwx.titan.monitor.helper.MemoryHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 系统监控-内存管理
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@RestController
@RequestMapping(value = "/system/memory/", method = RequestMethod.POST)
public class MemoryHandler {

    @RequestMapping("info")
    public MemoryBean info() {
        return MemoryHelper.getMemoryInformations();
    }

}
