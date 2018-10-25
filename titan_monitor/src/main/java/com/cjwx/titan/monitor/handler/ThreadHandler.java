package com.cjwx.titan.monitor.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.engine.util.file.ExcelUtils;
import com.cjwx.titan.monitor.bean.ThreadBean;
import com.cjwx.titan.monitor.helper.ThreadHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@RestHandler("系统监控-线程管理")
@RequestMapping(value = "/system/thread/", method = RequestMethod.POST)
public class ThreadHandler {

    @RequestMapping("list")
    public PageList<ThreadBean> list(@RequestBody Model model) {
        return ThreadHelper.findThreadList(model.getStart(), model.getSize(), model.getParams(ThreadBean.class));
    }

    @RequestMapping("download")
    public void download(@RequestBody Model model) {
        String[] title = {"线程编号", "线程名"};
        String[][] data = ThreadHelper.findThreadList(model.getParams(ThreadBean.class));
        ExcelUtils.download(title, data);
    }

    @RequestMapping("interrupt")
    public int interrupt(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return ThreadHelper.interruptThread(ids);
    }

}
