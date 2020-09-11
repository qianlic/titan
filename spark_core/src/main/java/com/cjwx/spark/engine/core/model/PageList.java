package com.cjwx.spark.engine.core.model;

import lombok.Data;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: 页面
 * @Author: qian li
 * @Date: 2018年03月29日 11:02
 */
@Data
public class PageList<T> {

    private int start;

    private int limit;

    private int total;

    private List<T> list;

    public PageList(int start, int limit) {
        this.start = start;
        this.limit = limit;
    }

    public PageList(Page<T> page) {
        this(page.getNumber(), page.getSize());
        this.total = page.getTotalPages();
        this.list = page.getContent();
    }

    public static <T> PageList<T> of(Page<T> page) {
        return new PageList<>(page);
    }

    public static <T> PageList<T> of(JpaRepository<T, Long> jpa, T param, int start, int size) {
        if (param == null) {
            return PageList.of(jpa.findAll(PageRequest.of(start, size)));
        }
        return PageList.of(jpa.findAll(Example.of(param), PageRequest.of(start, size)));
    }

}
