package com.itheima.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itheima.es.dao.EsArticleDao;
import com.itheima.es.domain.EsArticle;
import com.itheima.jpa.dao.ArticleDao;
import com.itheima.jpa.domain.Article;
import com.itheima.mongodb.dao.ArticleCommentDao;
import com.itheima.mongodb.domain.ArticleComment;
import com.itheima.service.ArticleService;
import com.itheima.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:35
 */
@Service
public class ArticleServiceImpl implements ArticleService {


    private final ArticleDao articleDao;

    private final EsArticleDao esArticleDao;

    private final RedisTemplate<String, String> redisTemplate;

    private final ArticleCommentDao articleCommentDao;

    public ArticleServiceImpl(ArticleDao articleDao, EsArticleDao esArticleDao, RedisTemplate redisTemplate, IdWorker idWorker, ArticleCommentDao articleCommentDao) {
        this.articleDao = articleDao;
        this.esArticleDao = esArticleDao;
        this.redisTemplate = redisTemplate;
        this.articleCommentDao = articleCommentDao;
    }

    /**
     * 保存文章
     *
     * @param article 文章参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveArticle(Article article) {

        articleDao.save(article);
        redisTemplate.delete("article");
        EsArticle esArticle = new EsArticle();
        BeanUtils.copyProperties(article, esArticle);
        esArticleDao.save(esArticle);
    }


    /**
     * 更新文章
     *
     * @param article 文章参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(Article article) {
        Optional<Article> optionalArticle = articleDao.findById(article.getId());
        if (!optionalArticle.isPresent()) {
            throw new RuntimeException("暂无对应数据,请重试!");
        } else {
            Article articleDataBase = optionalArticle.get();
            BeanUtils.copyProperties(article, articleDataBase);
            articleDao.save(articleDataBase);
            redisTemplate.delete("article");
            EsArticle esArticle = new EsArticle();
            BeanUtils.copyProperties(article, esArticle);
            esArticleDao.save(esArticle);
        }
    }


    /**
     * 删除文章
     *
     * @param aid 文章id
     */
    @Override
    public void deleteByAid(String aid) {
        Optional<Article> optionalArticle = articleDao.findById(aid);
        if (!optionalArticle.isPresent()) {
            throw new RuntimeException("暂无对应数据,请重试!");
        }
        articleDao.deleteById(aid);
        esArticleDao.deleteById(aid);
        List<ArticleComment> articleCommentList = articleCommentDao.findByArticleId(aid);
        redisTemplate.delete("article");
        articleCommentDao.deleteAll(articleCommentList);
    }


    /**
     * 最新文章列表
     *
     * @return List<EsArticle>
     */
    @Override
    public List<Article> findNewArticleList() {
        //先从redis中查
        String value = redisTemplate.opsForValue().get("article");
        //如果redis中没有数据
        if (StringUtils.isEmpty(value)) {
            //则从数据库中查找,并且将查找的结果存到redis中
            Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("createTime")));
            Page<Article> articlePage = articleDao.findAll(pageable);
            if (!articlePage.getContent().isEmpty()) {
                value = JSONObject.toJSONString(articlePage.getContent());
                redisTemplate.opsForValue().set("article", value);
            }
        }
        return JSONObject.parseArray(value, Article.class);
    }


    /**
     * 文章检索
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示的条数
     * @param keyword  搜索关键字
     * @return List<EsArticle>
     */
    @Override
    public List<EsArticle> search(Integer pageNum, Integer pageSize, String keyword) {
        if (pageNum == null || pageNum < 0) {
            pageNum = 0;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize);

        String selectCondition = keyword;
        if (!StringUtils.isEmpty(keyword)) {
            selectCondition = keyword.trim();
        }

        List<EsArticle> esArticles = esArticleDao.findByTitleLikeOrContentLike(selectCondition, selectCondition, pageable);
        return esArticles;
    }
}
