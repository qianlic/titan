package com.cjwx.titan.server.handler;

import com.alibaba.fastjson.JSONArray;
import com.cjwx.titan.engine.core.model.Model;
import com.cjwx.titan.engine.core.model.PageList;
import com.cjwx.titan.engine.core.web.annotation.RestHandler;
import com.cjwx.titan.server.bean.ComArticleBean;
import com.cjwx.titan.server.bean.SysUserBean;
import com.cjwx.titan.server.service.ArticleService;
import com.cjwx.titan.server.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * @Author: qian li
 * @Date: 2018年08月01日 16:12
 */
@Slf4j
@RestHandler("文章管理接口")
@RequestMapping(value = "/system/article/", method = RequestMethod.POST)
public class ArticleHandler {

    @Resource
    private ArticleService articleService;

    @RequestMapping("list")
    public PageList<ComArticleBean> list(@RequestBody Model model) {
        return articleService.getArticleList(model.getStart(), model.getSize(), model.getParams(ComArticleBean.class));
    }

    @RequestMapping("create")
    public void create(@RequestBody ComArticleBean article) {
        SysUserBean user = TokenUtils.getCurrentUser();
        article.setAuthor(user.getUsercode());
        articleService.createArticle(article);
    }

    @RequestMapping("edit")
    public int edit(@RequestBody Model model) {
        return articleService.updateArticle(model.getId(), model.getParams(ComArticleBean.class));
    }

    @RequestMapping("remove")
    public int remove(@RequestBody Model model) {
        JSONArray ids = model.getJSONArray("ids");
        return articleService.deleteArticle(ids);
    }

}
