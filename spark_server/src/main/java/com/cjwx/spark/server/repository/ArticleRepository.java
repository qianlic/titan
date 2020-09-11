package com.cjwx.spark.server.repository;

import com.cjwx.spark.server.entity.ComArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ComArticleEntity, Long> {

    int deleteByIdIn(List<Long> ids);

}
