package com.cjwx.titan.crawler.service;

import com.cjwx.titan.crawler.bean.ClrPageBean;
import com.cjwx.titan.crawler.bean.ClrWebUrlBean;
import com.cjwx.titan.engine.core.model.PageList;

import java.util.List;
import java.util.Map;

public interface WebUrlService {

    void createPage(ClrPageBean page);

    int deletePage(List ids);

    int deleteWebUrl(List ids);

    ClrPageBean getPage(int id);

    PageList<ClrWebUrlBean> getWebUrlList(int start, int size, Map<String, Object> whereCondition);

}
