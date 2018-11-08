package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.web.annotation.RestHandler;
import com.cjwx.titan.engine.web.annotation.RestMethod;
import com.cjwx.titan.server.bean.ComArticleBean;
import com.cjwx.titan.server.bean.SysUserBean;
import com.cjwx.titan.server.service.ArticleService;
import com.cjwx.titan.server.util.TokenUtils;
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
    public PageList<ComArticleBean> list(@RequestBody Model model) {
        return articleService.getArticleList(model.getStart(), model.getSize(), model.getParams(ComArticleBean.class));
    }

    @RestMethod("create")
    public void create(@RequestBody ComArticleBean article) {
        SysUserBean user = TokenUtils.getCurrentUser();
        article.setAuthor(user.getUsercode());
        articleService.createArticle(article);
    }

    @RestMethod("edit")
    public int edit(@RequestBody Model model) {
        return articleService.updateArticle(model.getId(), model.getParams(ComArticleBean.class));
    }

    @RestMethod("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return articleService.deleteArticle(ids);
    }

}
