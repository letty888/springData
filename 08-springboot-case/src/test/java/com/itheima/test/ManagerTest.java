package com.itheima.test;

import com.itheima.SpringbootCaseApplication;
import com.itheima.es.domain.EsArticle;
import com.itheima.jpa.domain.Article;
import com.itheima.service.ArticleService;
import com.itheima.utils.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.DeleteQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 17:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootCaseApplication.class)
public class ManagerTest {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ArticleService articleService;


    @Test
    public void testSaveArticle() {

        //准备测试数据

        Article article = new Article();
        String articleId = idWorker.nextId() + "";
        article.setId(articleId);
        article.setTitle("6黑马程序员介绍");
        article.setAuthor("6黑马程序员");
        article.setCreateTime(new Date());
        article.setContent("6黑马程序员专注于IT培训,Java培训,人工智能培训,Python培训,大数据培训,区\n" +
                "块链培训,UI设计培训,PHP培训,Web前端培训,软件测试培训,产品经理培训，并提供Java培训,大数据培训,区块链培训,UI\n" +
                "设计培训,PHP培训,软件测试培训,产品经理培训等服务。");

        articleService.saveArticle(article);
    }

    @Test
    public void testUpdateArticle() {

        //准备测试数据
        Article article = new Article();
        article.setId("1251874469782294528");
        article.setTitle("1黑马程序员介绍");
        article.setAuthor("1黑马程序员");
        article.setCreateTime(new Date());
        article.setContent("1黑马程序员专注于IT培训,Java培训,人工智能培训,Python培训,大数据培训,区\n" +
                "块链培训,UI设计培训,PHP培训,Web前端培训,软件测试培训,产品经理培训，并提供Java培训,大数据培训,区块链培训,UI\n" +
                "设计培训,PHP培训,软件测试培训,产品经理培训等服务。");
        articleService.updateArticle(article);
    }

    @Test
    public void testDeleteArticle() {
        String aid = "1251874469782294528";
        articleService.deleteByAid(aid);
    }

    @Test
    public void testFindNewArticleList(){
        List<Article> newArticleList = articleService.findNewArticleList();
        System.out.println(newArticleList);
    }


    @Test
    public void testSearch(){
        List<EsArticle> esArticleList = articleService.search(0, 10, "程序员");
        System.out.println(esArticleList);
    }
}
