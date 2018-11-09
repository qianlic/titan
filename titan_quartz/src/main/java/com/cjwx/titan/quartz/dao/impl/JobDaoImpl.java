package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.JobDao;
import com.cjwx.titan.quartz.bean.QtzJobBean;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:12
 */
@Repository
public class JobDaoImpl extends BaseDao implements JobDao {

    private static final String CUSTOM_TABLE = "qtz_job_details";
    private static final String CUSTOM_COLUMS = "JOB_NAME 'name',JOB_GROUP 'group'";

    @Override
    public PageList<QtzJobBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(QtzJobBean.class).table(CUSTOM_TABLE)
                .select(CUSTOM_COLUMS).eq(where).page(start, size);
    }

}
