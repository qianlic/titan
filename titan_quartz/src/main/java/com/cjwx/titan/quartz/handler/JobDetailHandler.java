package com.cjwx.titan.quartz.handler;

import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.quartz.entity.QtzJobEntity;
import com.cjwx.titan.quartz.service.JobDetilService;
import org.quartz.JobKey;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@RestHandler("定时任务-任务管理")
@RequestMapping(value = "/system/jobDetail/", method = RequestMethod.POST)
public class JobDetailHandler {

    @Resource
    private JobDetilService jobDetilService;

    @RequestMapping("info")
    public List<JobKey> all() {
        return jobDetilService.getJobKeyList();
    }

    @RequestMapping("list")
    public PageList<QtzJobEntity> list(@RequestBody Model model) {
        return jobDetilService.getJobDetailList(model.getStart(), model.getSize(), model.getParams(QtzJobEntity.class));
    }

    @RequestMapping("create")
    public void create(@RequestBody QtzJobEntity job) {
        jobDetilService.createJobDetail(job);
    }

    @RequestMapping("edit")
    public void edit(@RequestBody QtzJobEntity job) {
        jobDetilService.updateJobDetail(job);
    }

    @RequestMapping("remove")
    public void remove(@RequestBody QtzJobEntity job) {
        jobDetilService.delete(job);
    }

    @RequestMapping("run")
    public void run(@RequestBody QtzJobEntity job) {
        jobDetilService.execute(job);
    }

    @RequestMapping("pause")
    public void pause(@RequestBody QtzJobEntity job) {
        jobDetilService.pause(job);
    }

    @RequestMapping("resume")
    public void resume(@RequestBody QtzJobEntity job) {
        jobDetilService.resume(job);
    }

}
