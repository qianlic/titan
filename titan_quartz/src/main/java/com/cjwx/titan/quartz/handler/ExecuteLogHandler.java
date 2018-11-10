package com.cjwx.titan.quartz.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.quartz.bean.QtzExecuteLogBean;
import com.cjwx.titan.quartz.service.ExecuteLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月07日 20:03
 */
@Api(tags = "定时任务-调度管理")
@RestHandler("/execute/")
public class ExecuteLogHandler {

    @Resource
    private ExecuteLogService executeLogService;

    @RestMethod("list")
    public PageList<QtzExecuteLogBean> list(@RequestBody Model model) {
        return executeLogService.getLogList(model.getStart(), model.getSize(), model.getParams(QtzExecuteLogBean.class));
    }

    @RestMethod("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return executeLogService.delete(ids);
    }

}
