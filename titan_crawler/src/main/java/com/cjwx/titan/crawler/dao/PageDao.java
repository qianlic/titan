package com.cjwx.titan.crawler.dao;

import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface PageDao {

    void createPage(ClrPageBean page);

    int deletePage(List ids);

    ClrPageBean findPage(int id);

}
