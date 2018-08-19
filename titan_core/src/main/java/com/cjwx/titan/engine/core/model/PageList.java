package com.cjwx.titan.engine.core.model;

import lombok.Data;

import java.util.List;

/**
 * @Description: 页面
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class PageList<E> {

    private int start;

    private int limit;

    private int total;

    private List<E> list;

    public PageList(int start, int limit){
        this.start = start;
        this.limit = limit;
    }

}
