package com.cjwx.titan.quartz.handler;

import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.quartz.bean.QtzScheduleJobBean;
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
    public PageList<QtzScheduleJobBean> list(@RequestBody Model model) {
        return scheduleService.getScheduleList(model.getStart(), model.getSize(), model.getParams(QtzScheduleJobBean.class));
    }

    @RestMethod("create")
    public void create(@RequestBody QtzScheduleJobBean job) {
        scheduleService.create(job);
    }

    @RestMethod("edit")
    public void edit(@RequestBody QtzScheduleJobBean job) {
        scheduleService.update(job);
    }

    @RestMethod("remove")
    public void remove(@RequestBody QtzScheduleJobBean job) {
        scheduleService.delete(job);
    }

    @RestMethod("pause")
    public void pause(@RequestBody QtzScheduleJobBean job) {
        scheduleService.pause(job);
    }

    @RestMethod("resume")
    public void resume(@RequestBody QtzScheduleJobBean job) {
        scheduleService.resume(job);
    }

    @RestMethod("start")
    public void start(@RequestBody QtzScheduleJobBean job) {
        scheduleService.start(job);
    }

}
