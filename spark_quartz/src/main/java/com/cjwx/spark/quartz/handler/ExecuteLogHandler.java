package com.cjwx.spark.quartz.handler;

import com.cjwx.spark.engine.core.model.Model;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.quartz.entity.QtzExecuteLogEntity;
import com.cjwx.spark.quartz.service.ExecuteLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

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
    public PageList<QtzExecuteLogEntity> list(@RequestBody Model<QtzExecuteLogEntity> model) {
        return executeLogService.getLogList(model.getStart(), model.getSize(), model.getParams());
    }

    @RestMethod("remove")
    public int remove(@RequestBody List<Long> ids) {
        return executeLogService.delete(ids);
    }

}
