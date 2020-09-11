package com.cjwx.spark.server.repository;

import com.cjwx.spark.server.entity.ComImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ComImageEntity, Long> {

    int deleteByHash(String hash);

}
