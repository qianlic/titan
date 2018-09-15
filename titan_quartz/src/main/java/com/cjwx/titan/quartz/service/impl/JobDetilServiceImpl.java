package com.cjwx.titan.quartz.service.impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.JobDetailDao;
import com.cjwx.titan.quartz.entity.QtzJobEntity;
import com.cjwx.titan.quartz.execute.BaseExecService;
import com.cjwx.titan.quartz.service.JobDetilService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 定时任务服务
 * @Author: qian li
 * @Date: 2018年03月29日 19:09
 */
@Slf4j
@Service
@Transactional
public class JobDetilServiceImpl implements JobDetilService {

    @Resource
    private Scheduler scheduler;
    @Resource
    private JobDetailDao jobDetailDao;

    @Override
    public List<JobKey> getJobKeyList() {
        return jobDetailDao.findJobDetailList().stream().map(QtzJobEntity::getJobKey).collect(Collectors.toList());
    }

    @Override
    public PageList<QtzJobEntity> getJobDetailList(int start, int size, Map<String, Object> wheres) {
        PageList<QtzJobEntity> page = jobDetailDao.findJobDetailList(start, size, wheres);
        page.setList(page.getList().stream().map(this::getJobEntity).collect(Collectors.toList()));
        return page;
    }

    /**
     * 创建定时任务
     */
    @Override
    public void createJobDetail(QtzJobEntity job) {
        try {
            JobDetail j = JobBuilder.newJob(BaseExecService.class)
                    .withIdentity(job.getName(), job.getGroup())
                    .withDescription(job.getDescription())
                    .usingJobData("SERVICE", job.getService())
                    .usingJobData("METHOD", job.getMethod())
                    .usingJobData("DATA", job.getData())
                    .storeDurably(true)
                    .build();
            scheduler.addJob(j, true);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 更新任务参数
     */
    @Override
    public void updateJobDetail(QtzJobEntity job) {
        try {
            JobDetail jobDetail = getJobDetail(job).getJobBuilder()
                    .withDescription(job.getDescription())
                    .usingJobData("SERVICE", job.getService())
                    .usingJobData("METHOD", job.getMethod())
                    .usingJobData("DATA", job.getData())
                    .build();
            scheduler.addJob(jobDetail, true);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 获取任务参数
     */
    @Override
    public QtzJobEntity getJobEntity(QtzJobEntity job) {
        try {
            JobDetail jobDetail = getJobDetail(job);
            JobDataMap data = jobDetail.getJobDataMap();
            job.setService(data.getString("SERVICE"));
            job.setMethod(data.getString("METHOD"));
            job.setData(data.getString("DATA"));
            job.setDescription(jobDetail.getDescription());
            job.setTriggerKeys(scheduler.getTriggersOfJob(job.getJobKey()).stream()
                    .map(Trigger::getKey)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
        return job;
    }

    /**
     * 查询运行中的任务
     */
    @Override
    public List<QtzJobEntity> selectRun() throws SchedulerException {
        return scheduler.getCurrentlyExecutingJobs().stream()
                .map(JobExecutionContext::getJobDetail)
                .map(j -> {
                    JobKey k = j.getKey();
                    JobDataMap d = j.getJobDataMap();
                    QtzJobEntity job = new QtzJobEntity();
                    job.setName(k.getName());
                    job.setGroup(k.getGroup());
                    job.setService(d.getString("SERVICE"));
                    job.setMethod(d.getString("METHOD"));
                    job.setData(d.getString("DATA"));
                    job.setDescription(j.getDescription());
                    try {
                        job.setTriggerKeys(scheduler.getTriggersOfJob(k).stream()
                                .map(Trigger::getKey)
                                .collect(Collectors.toList()));
                    } catch (Exception e) {
                        log.debug(e.getMessage(), e);
                    }
                    return job;
                }).collect(Collectors.toList());
    }

    /**
     * 获取单个任务信息
     */
    @Override
    public JobDetail getJobDetail(QtzJobEntity job) throws SchedulerException {
        log.debug("获取JOB:" + job.getName());
        return scheduler.getJobDetail(job.getJobKey());
    }

    /**
     * 删除定时任务
     */
    @Override
    public boolean delete(QtzJobEntity job) {
        try {
            log.debug("删除JOB:" + job.getName());
            return scheduler.deleteJob(job.getJobKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 执行单个定时任务
     */
    @Override
    public void execute(QtzJobEntity job) {
        try {
            log.debug("运行JOB:" + job.getName());
            scheduler.triggerJob(job.getJobKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 暂停单个定时任务
     */
    @Override
    public void pause(QtzJobEntity job) {
        try {
            log.debug("暂停JOB:" + job.getName());
            scheduler.pauseJob(job.getJobKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

    /**
     * 恢复单个定时任务
     */
    @Override
    public void resume(QtzJobEntity job) {
        try {
            log.debug("恢复JOB:" + job.getName());
            scheduler.resumeJob(job.getJobKey());
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

}
