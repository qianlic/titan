package com.cjwx.titan.quartz.dao;

import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzExecuteLogBean;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 14:47
 */
public interface LogDao {

    void insert(Object bean);

    int delete(List ids);

    PageList<QtzExecuteLogBean> select(int start, int size, Map<String, Object> where);

}
