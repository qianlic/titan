package com.cjwx.spark.quartz.handler;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.quartz.dto.QtzExecuteLogDTO;
import com.cjwx.spark.quartz.service.ExecuteLogService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 定时任务-调度管理
 * @Author: qian li
 * @Date: 2018年11月07日 20:03
 */
@Api(tags = "定时任务-调度管理")
@RestHandler("/execute/")
public class ExecuteLogHandler {

    @Resource
    private ExecuteLogService executeLogService;

    @RestMethod("list")
    public ResultDTO<PageDTO<QtzExecuteLogDTO>> list(@RequestBody QtzExecuteLogDTO log) throws Exception {
        return executeLogService.getLogList(log, log.getStart(), log.getSize());
    }

    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody List<Long> ids) {
        return executeLogService.delete(ids);
    }

}
