package com.cjwx.titan.server.dao.impl;

import com.cjwx.titan.engine.core.base.dao.BaseDao;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.server.bean.ComImageBean;
import com.cjwx.titan.server.dao.ImageDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ImageDaoImpl extends BaseDao implements ImageDao {

    @Override
    public int delete(String hash) {
        return this.getExecute().table(ComImageBean.TABLE).eq("hash", hash).delete();
    }

    @Override
    public PageList<ComImageBean> select(int start, int size, Map<String, Object> where) {
        return this.getQuery().from(ComImageBean.TABLE).eq(where).page(start, size, ComImageBean.class);
    }

}
