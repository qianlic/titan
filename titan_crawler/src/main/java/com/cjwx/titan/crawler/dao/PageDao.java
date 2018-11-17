package com.cjwx.titan.crawler.dao;

import com.cjwx.titan.crawler.bean.ClrPageBean;

import java.util.List;

public interface PageDao {

    void insert(Object bean);

    int delete(List ids);

    ClrPageBean select(int id);

}
