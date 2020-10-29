package com.cjwx.spark.engine.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;

import java.util.List;

/**
 * @Description: Mapper工具
 * @Author: qian li
 * @Date: 2020/10/28 15:43
 */
public class MapperUtils {

    /**
     * 新增
     */
    public static <T> ResultDTO<Integer> insert(BaseMapper<T> mapper, T entity) {
        return ResultUtils.success(mapper.insert(entity));
    }

    /**
     * 新增
     */
    public static <T> ResultDTO<Integer> update(BaseMapper<T> mapper, T entity) {
        return ResultUtils.success(mapper.updateById(entity));
    }

    /**
     * 新增
     */
    public static <T> ResultDTO<Integer> update(BaseMapper<T> mapper, Wrapper<T> wrapper) {
        return ResultUtils.success(mapper.update(null, wrapper));
    }

    /**
     * 删除
     */
    public static <T> ResultDTO<Integer> delete(BaseMapper<T> mapper, List<Long> ids) {
        return ResultUtils.success(mapper.deleteBatchIds(ids));
    }

    /**
     * 删除
     */
    public static <T> ResultDTO<Integer> delete(BaseMapper<T> mapper, Wrapper<T> wrapper) {
        return ResultUtils.success(mapper.delete(wrapper));
    }

    /**
     * 查询
     */
    public static <T> ResultDTO<T> select(BaseMapper<T> mapper, Wrapper<T> wrapper) {
        return ResultUtils.success(mapper.selectOne(wrapper));
    }

    public static <T, E> ResultDTO<E> select(BaseMapper<T> mapper, Wrapper<T> wrapper, Class<E> clz) throws Exception {
        return ResultUtils.success(ObjectUtils.convert(mapper.selectOne(wrapper), clz));
    }

    /**
     * 查询
     */
    public static <T> ResultDTO<List<T>> list(BaseMapper<T> mapper, T entity) {
        return list(mapper, new QueryWrapper<>(entity));
    }

    /**
     * 查询
     */
    public static <T> ResultDTO<List<T>> list(BaseMapper<T> mapper, List<Long> ids) {
        return ResultUtils.success(mapper.selectBatchIds(ids));
    }

    /**
     * 查询
     */
    public static <T> ResultDTO<List<T>> list(BaseMapper<T> mapper, Wrapper<T> wrapper) {
        return ResultUtils.success(mapper.selectList(wrapper));
    }

    /**
     * 查询
     */
    public static <T, E> ResultDTO<List<E>> list(BaseMapper<T> mapper, Class<E> clz) throws Exception {
        return ResultUtils.success(ObjectUtils.convert(mapper.selectList(null), clz));
    }

    /**
     * 查询
     */
    public static <T, E> ResultDTO<List<E>> list(BaseMapper<T> mapper, T entity, Class<E> clz) throws Exception {
        return list(mapper, new QueryWrapper<>(entity), clz);
    }

    /**
     * 查询
     */
    public static <T, E> ResultDTO<List<E>> list(BaseMapper<T> mapper, List<Long> ids, Class<E> clz) throws Exception {
        return ResultUtils.success(ObjectUtils.convert(mapper.selectBatchIds(ids), clz));
    }

    /**
     * 查询
     */
    public static <T, E> ResultDTO<List<E>> list(BaseMapper<T> mapper, Wrapper<T> wrapper, Class<E> clz) throws Exception {
        return ResultUtils.success(ObjectUtils.convert(mapper.selectList(wrapper), clz));
    }

    /**
     * 列表页
     */
    public static <T> ResultDTO<PageDTO<T>> pageList(BaseMapper<T> mapper, T entity, int start, int size) {
        return pageList(mapper, new QueryWrapper<>(entity), start, size);
    }

    /**
     * 列表页
     */
    public static <T> ResultDTO<PageDTO<T>> pageList(BaseMapper<T> mapper, Wrapper<T> wrapper, int start, int size) {
        return pageList(mapper.selectPage(new Page<>(start, size), wrapper));
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
        return ResultUtils.success(pageDTO);
    }

    /**
     * 列表页
     */
    public static <T, E> ResultDTO<PageDTO<E>> pageList(BaseMapper<T> mapper, T entity, int start, int size, Class<E> clz) throws Exception {
        return pageList(mapper, new QueryWrapper<>(entity), start, size, clz);
    }

    /**
     * 列表页
     */
    public static <T, E> ResultDTO<PageDTO<E>> pageList(BaseMapper<T> mapper, Wrapper<T> wrapper, int start, int size, Class<E> clz) throws Exception {
        return pageList(mapper.selectPage(new Page<>(start, size), wrapper), clz);
    }

    /**
     * 列表页
     */
    public static <T, E> ResultDTO<PageDTO<E>> pageList(Page<T> page, Class<E> clz) throws Exception {
        PageDTO<E> pageDTO = new PageDTO<>();
        pageDTO.setStart(page.getCurrent());
        pageDTO.setLimit(page.getSize());
        pageDTO.setTotal(page.getTotal());
        pageDTO.setList(ObjectUtils.convert(page.getRecords(), clz));
        return ResultUtils.success(pageDTO);
    }

}
