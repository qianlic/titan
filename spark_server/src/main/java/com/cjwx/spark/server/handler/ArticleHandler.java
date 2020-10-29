package com.cjwx.spark.server.handler;

import com.cjwx.spark.engine.core.dto.PageDTO;
import com.cjwx.spark.engine.core.dto.ResultDTO;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.dto.ComArticleDTO;
import com.cjwx.spark.server.dto.SysUserDTO;
import com.cjwx.spark.server.service.ArticleService;
import com.cjwx.spark.server.util.TokenUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@Api(tags = "文章管理接口")
@RestHandler("/article/")
public class ArticleHandler {

    @Resource
    private ArticleService articleService;

    @RestMethod("create")
    public ResultDTO<Integer> create(@RequestBody ComArticleDTO article) throws Exception {
        SysUserDTO user = TokenUtils.getCurrentUser();
        article.setAuthor(user.getUserCode());
        return articleService.createArticle(article);
    }

    @RestMethod("edit")
    public ResultDTO<Integer> edit(@RequestBody ComArticleDTO article) throws Exception {
        return articleService.updateArticle(article);
    }

    @RestMethod("remove")
    public ResultDTO<Integer> remove(@RequestBody List<Long> ids) {
        return articleService.deleteArticle(ids);
    }

    @RestMethod("list")
    public ResultDTO<PageDTO<ComArticleDTO>> list(@RequestBody ComArticleDTO article) throws Exception {
        return articleService.getArticleList(article, article.getStart(), article.getSize());
    }

}
