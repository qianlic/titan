package com.cjwx.titan.crawler.dao.impl;

import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.crawler.dao.PageDao;
import com.cjwx.titan.engine.core.base.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageDaoImpl extends BaseDao implements PageDao {

    @Override
    public int delete(List ids) {
        return this.getExecute().table(ClrPageBean.TABLE).in("id", ids).delete();
    }

    @Override
    public ClrPageBean select(int id) {
        return (ClrPageBean) this.getQuery().from(ClrPageBean.TABLE).eq("id", id).uniqueResult();
    }

}
