package com.cjwx.spark.server.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjwx.spark.server.entity.ComArticle;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends BaseMapper<ComArticle> {

}
