package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzScheduleLogBean;
import com.cjwx.titan.quartz.dao.LogDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 14:47
 */

@Repository
public class LogDaoImpl extends BaseDao implements LogDao {

    @Override
    public PageList<QtzScheduleLogBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(QtzScheduleLogBean.class).eq(where).page(start, size);
    }

}
