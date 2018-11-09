package com.cjwx.titan.quartz.service.impl;

import com.alibaba.fastjson.JSON;
import com.cjwx.titan.quartz.bean.QtzScheduleLogBean;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import com.cjwx.titan.quartz.dao.LogDao;
import com.cjwx.titan.quartz.service.ExecuteLogService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
@Service
@Transactional
public class ExecuteLogServiceImpl implements ExecuteLogService {

    @Resource
    private LogDao logDao;

    @Override
    public void create(JobExecutionContext job) {
        QtzScheduleLogBean log = new QtzScheduleLogBean();
        JobDetail jobDetail = job.getJobDetail();
        log.setJobname(jobDetail.getKey().getName());
        log.setJobgroup(jobDetail.getKey().getGroup());
        log.setServer(jobDetail.getJobDataMap().getString(QuartzConfiguration.SERVER_KEY));
        log.setPath(jobDetail.getJobDataMap().getString(QuartzConfiguration.PATH_KEY));
        log.setData(jobDetail.getJobDataMap().getString(QuartzConfiguration.DATA_KEY));
        log.setResult(JSON.toJSONString(job.getResult()));
        logDao.insert(log);
    }

}
