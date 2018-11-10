package com.cjwx.titan.quartz.service.impl;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzExecuteLogBean;
import com.cjwx.titan.quartz.dao.LogDao;
import com.cjwx.titan.quartz.service.ExecuteLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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
    public void create(QtzExecuteLogBean log) {
        logDao.insert(log);
    }

    @Override
    public int delete(List ids) {
        return logDao.delete(ids);
    }

    @Override
    public PageList<QtzExecuteLogBean> getLogList(int start, int size, Map<String, Object> wheres) {
        return logDao.select(start, size, wheres);
    }

}
