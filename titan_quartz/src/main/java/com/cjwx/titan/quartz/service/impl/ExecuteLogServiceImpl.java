package com.cjwx.titan.quartz.service.impl;

import com.alibaba.fastjson.JSON;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzLogBean;
import com.cjwx.titan.quartz.config.QuartzConfiguration;
import com.cjwx.titan.quartz.dao.LogDao;
import com.cjwx.titan.quartz.service.ExecuteLogService;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

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
        QtzLogBean log = new QtzLogBean();
        JobDetail jobDetail = job.getJobDetail();
        log.setJobname(jobDetail.getKey().getName());
        log.setJobgroup(jobDetail.getKey().getGroup());
        log.setServer(jobDetail.getJobDataMap().getString(QuartzConfiguration.SERVER_KEY));
        log.setPath(jobDetail.getJobDataMap().getString(QuartzConfiguration.PATH_KEY));
        log.setData(jobDetail.getJobDataMap().getString(QuartzConfiguration.DATA_KEY));
        log.setResult(JSON.toJSONString(job.getResult()));
        logDao.insert(log);
    }

    @Override
    public PageList<QtzLogBean> getLogList(int start, int size, Map<String, Object> wheres) {
        return logDao.select(start, size, wheres);
    }

}
