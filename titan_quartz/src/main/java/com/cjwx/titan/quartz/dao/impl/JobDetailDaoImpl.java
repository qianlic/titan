package com.cjwx.titan.quartz.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.quartz.dao.JobDetailDao;
import com.cjwx.titan.quartz.entity.QtzJobEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2018年08月03日 9:12
 */
@Repository
public class JobDetailDaoImpl extends BaseDao implements JobDetailDao {

    private static final String CUSTOM_TABLE = "qtz_job_details";
    private static final String CUSTOM_COLUMS = "JOB_NAME 'name',JOB_GROUP 'group'";

    @Override
    public List<QtzJobEntity> select() {
        return this.getQuery(QtzJobEntity.class).table(CUSTOM_TABLE).select(CUSTOM_COLUMS).list();
    }

    @Override
    public PageList<QtzJobEntity> select(int start, int size, Map<String, Object> where) {
        return this.getQuery(QtzJobEntity.class).table(CUSTOM_TABLE)
                .select(CUSTOM_COLUMS).eq(where).page(start, size);
    }

}
