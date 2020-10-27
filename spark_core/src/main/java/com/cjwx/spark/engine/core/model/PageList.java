package com.cjwx.spark.engine.core.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * @Description: 页面
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class PageList<T> {

    private long start;

    private long limit;

    private long total;

    private List<T> list;

    public PageList(long start, long limit) {
        this.start = start;
        this.limit = limit;
    }

    public PageList(IPage<T> page) {
        this(page.getCurrent(), page.getSize());
        this.total = page.getTotal();
        this.list = page.getRecords();
    }

    public static <T> PageList<T> of(Page<T> page) {
        return new PageList<>(page);
    }

    public static <T> PageList<T> of(BaseMapper<T> mapper, T param, int start, int size) {
        QueryWrapper<T> query = new QueryWrapper<>(param);
        Page<T> page = new Page<>(start, size);
        return PageList.of(mapper.selectPage(page, query));
    }

}
