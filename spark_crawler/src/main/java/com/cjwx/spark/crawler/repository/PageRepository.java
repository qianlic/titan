package com.cjwx.spark.crawler.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjwx.spark.crawler.entity.ClrPage;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: qian li
 * @Date: 2020/10/29 13:43
 */
@Repository
public interface PageRepository extends BaseMapper<ClrPage> {
}