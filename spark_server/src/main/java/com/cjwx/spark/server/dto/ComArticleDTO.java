package com.cjwx.spark.server.dto;

import com.cjwx.spark.engine.core.dto.BaseDTO;
import lombok.Data;

@Data
public class ComArticleDTO extends BaseDTO {

    private String title;

    private String tags;

    private String content;

    private String author;

}
