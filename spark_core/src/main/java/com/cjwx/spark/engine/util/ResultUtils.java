package com.cjwx.spark.engine.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;

/**
 * @Description: 返回结果
 * @Author: qian li
 * @Date: 2020/10/28 14:19
 */
public class ResultUtils {

    /**
     * 成功
     */
    public static <T> ResultDTO<T> success() {
        ResultDTO<T> result = new ResultDTO<>();
        result.setSuccess(true);
        result.setCode("0");
        result.setMessage("操作成功!");
        return result;
    }

    /**
     * 成功
     */
    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> result = success();
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static <T> ResultDTO<T> fail(String code, String message) {
        ResultDTO<T> result = new ResultDTO<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 列表页
     */
    public static <T> ResultDTO<PageDTO<T>> pageList(Page<T> page) {
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setStart(page.getCurrent());
        pageDTO.setLimit(page.getSize());
        pageDTO.setTotal(page.getTotal());
        pageDTO.setList(page.getRecords());
        return success(pageDTO);
    }

    /**
     * 列表页
     */
    public static <T> ResultDTO<PageDTO<T>> pageList(BaseMapper<T> mapper, T param, int start, int size) {
        QueryWrapper<T> query = new QueryWrapper<>(param);
        Page<T> page = new Page<>(start, size);
        return pageList(mapper.selectPage(page, query));
    }

}
