package com.cjwx.spark.quartz.handler;

import com.cjwx.spark.engine.core.model.Model;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.quartz.entity.QtzJobBean;
import com.cjwx.spark.quartz.service.ScheduleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月07日 20:03
 */
@Api(tags = "定时任务-调度管理")
@RestHandler("/schedule/")
public class ScheduleHandler {

    @Resource
    private ScheduleService scheduleService;

    @RestMethod("list")
    public PageList<QtzJobBean> list(@RequestBody Model<QtzJobBean> model) {
        return scheduleService.getScheduleList(model.getStart(), model.getSize(), model.getParams());
    }

    @RestMethod("create")
    public void create(@RequestBody QtzJobBean job) {
        scheduleService.create(job);
    }

    @RestMethod("edit")
    public void edit(@RequestBody QtzJobBean job) {
        scheduleService.update(job);
    }

    @RestMethod("remove")
    public void remove(@RequestBody QtzJobBean job) {
        scheduleService.delete(job);
    }

    @RestMethod("pause")
    public void pause(@RequestBody QtzJobBean job) {
        scheduleService.pause(job);
    }

    @RestMethod("resume")
    public void resume(@RequestBody QtzJobBean job) {
        scheduleService.resume(job);
    }

    @RestMethod("start")
    public void start(@RequestBody QtzJobBean job) {
        scheduleService.start(job);
    }

}
