package com.cjwx.spark.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cjwx.spark.server.entity.ComArticle;
import com.cjwx.spark.server.util.TokenUtils;
import com.cjwx.spark.engine.core.model.Model;
import com.cjwx.spark.engine.core.model.PageList;
import com.cjwx.spark.engine.entity.SysUser;
import com.cjwx.spark.engine.web.annotation.RestHandler;
import com.cjwx.spark.engine.web.annotation.RestMethod;
import com.cjwx.spark.server.service.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@Api(tags = "文章管理接口")
@RestHandler("/article/")
public class ArticleHandler {

    @Resource
    private ArticleService articleService;

    @RestMethod("list")
    public PageList<ComArticle> list(@RequestBody Model<ComArticle> model) {
        return articleService.getArticleList(model.getStart(), model.getSize(), model.getParams());
    }

    @RestMethod("create")
    public void create(@RequestBody ComArticle article) {
        SysUser user = TokenUtils.getCurrentUser();
        article.setAuthor(user.getUserCode());
        articleService.createArticle(article);
    }

    @RestMethod("edit")
    public void edit(@RequestBody ComArticle article) {
        articleService.updateArticle(article);
    }

    @RestMethod("remove")
    public int remove(@RequestBody JSONObject param) {
        JSONArray ids = param.getJSONArray("ids");
        return articleService.deleteArticle(ids.toJavaList(Long.class));
    }

}
