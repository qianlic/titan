package com.cjwx.titan.quartz.handler;

import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.quartz.entity.QtzScheduleEntity;
import com.cjwx.titan.quartz.service.ScheduleService;
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
    ScheduleService scheduleService;

    @RestMethod("list")
    public PageList<QtzScheduleEntity> list(@RequestBody Model model) {
        return scheduleService.getScheduleList(model.getStart(), model.getSize(), model.getParams(QtzScheduleEntity.class));
    }

    @RestMethod("create")
    public void create(@RequestBody QtzScheduleEntity job) {
        scheduleService.create(job);
    }

    @RestMethod("edit")
    public void edit(@RequestBody QtzScheduleEntity job) {
        scheduleService.update(job);
    }

    @RestMethod("remove")
    public void remove(@RequestBody QtzScheduleEntity job) {
        scheduleService.delete(job);
    }

    @RestMethod("pause")
    public void pause(@RequestBody QtzScheduleEntity job) {
        scheduleService.pause(job);
    }

    @RestMethod("resume")
    public void resume(@RequestBody QtzScheduleEntity job) {
        scheduleService.resume(job);
    }

    @RestMethod("start")
    public void start(@RequestBody QtzScheduleEntity job) {
        scheduleService.start(job);
    }

}
