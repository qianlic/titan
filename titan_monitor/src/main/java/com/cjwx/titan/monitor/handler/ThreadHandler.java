package com.cjwx.titan.monitor.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.util.file.ExcelUtils;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.monitor.bean.ThreadBean;
import com.cjwx.titan.monitor.helper.ThreadHelper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: qian li
 * @Date: 2018年08月18日 5:39
 */
@Api(tags = "系统监控-线程管理")
@RestHandler("/thread/")
public class ThreadHandler {

    @RestMethod("list")
    public PageList<ThreadBean> list(@RequestBody Model model) {
        return ThreadHelper.findThreadList(model.getStart(), model.getSize(), model.getParams(ThreadBean.class));
    }

    @RestMethod("download")
    public void download(@RequestBody Model model) {
        String[] title = {"线程编号", "线程名"};
        String[][] data = ThreadHelper.findThreadList(model.getParams(ThreadBean.class));
        ExcelUtils.download(title, data);
    }

    @RestMethod("interrupt")
    public int interrupt(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return ThreadHelper.interruptThread(ids);
    }

}
