package com.cjwx.spark.quartz.handler;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.quartz.dto.QtzJobDTO;
import com.cjwx.spark.quartz.service.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Description: 定时任务-调度管理
 * @Author: qian li
 * @Date: 2018年11月07日 20:03
 */
@Api(tags = "定时任务-调度管理")
@RestHandler("/schedule/")
public class ScheduleHandler {

    @Resource
    private ScheduleService scheduleService;

    @RestMethod("list")
    public ResultDTO<PageDTO<QtzJobDTO>> list(@RequestBody QtzJobDTO job) throws Exception {
        return scheduleService.getScheduleList(job, job.getStart(), job.getSize());
    }

    @RestMethod("create")
    public void create(@RequestBody QtzJobDTO job) {
        scheduleService.create(job);
    }

    @RestMethod("edit")
    public void edit(@RequestBody QtzJobDTO job) {
        scheduleService.update(job);
    }

    @RestMethod("remove")
    public void remove(@RequestBody QtzJobDTO job) {
        scheduleService.delete(job);
    }

    @RestMethod("pause")
    public void pause(@RequestBody QtzJobDTO job) {
        scheduleService.pause(job);
    }

    @RestMethod("resume")
    public void resume(@RequestBody QtzJobDTO job) {
        scheduleService.resume(job);
    }

    @RestMethod("start")
    public void start(@RequestBody QtzJobDTO job) {
        scheduleService.start(job);
    }

}
