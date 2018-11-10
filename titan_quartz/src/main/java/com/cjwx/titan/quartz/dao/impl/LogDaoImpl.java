package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.bean.QtzExecuteLogBean;
import com.cjwx.titan.quartz.dao.LogDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年11月09日 14:47
 */
@Repository
public class LogDaoImpl extends BaseDao implements LogDao {

    @Override
    public int delete(List ids) {
        return this.getExecute(QtzExecuteLogBean.class).in("id", ids).delete();
    }

    @Override
    public PageList<QtzExecuteLogBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(QtzExecuteLogBean.class).eq(where).page(start, size);
    }

}
