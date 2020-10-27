package com.cjwx.spark.quartz.service;

import com.cjwx.spark.quartz.entity.QtzExecuteLog;
import com.cjwx.spark.engine.core.model.PageList;

import java.util.List;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 15:44
 */
public interface ExecuteLogService {

    void create(QtzExecuteLog log);

    int delete(List<Long> ids);

    PageList<QtzExecuteLog> getLogList(int start, int size, QtzExecuteLog wheres);

}
