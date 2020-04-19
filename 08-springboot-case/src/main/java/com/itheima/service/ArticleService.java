package com.itheima.service;

import com.itheima.es.domain.EsArticle;
import com.itheima.jpa.domain.Article;

import java.util.List;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:32
 */
public interface ArticleService {

    /**
     * 保存文章
     *
     * @param article 文章参数
     */
    void saveArticle(Article article);

    /**
     * 更新文章
     *
     * @param article 文章参数
     */
    void updateArticle(Article article);

    /**
     * 删除文章
     *
     * @param aid 文章id
     */
    void deleteByAid(String aid);

    /**
     * 最新文章列表
     *
     * @return List<EsArticle>
     */
    List<Article> findNewArticleList();

    /**
     * 文章检索
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示的条数
     * @param keyword  搜索关键字
     * @return List<EsArticle>
     */
    List<EsArticle> search(Integer pageNum, Integer pageSize, String keyword);
}
