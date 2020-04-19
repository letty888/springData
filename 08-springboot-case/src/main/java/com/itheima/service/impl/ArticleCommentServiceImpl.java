package com.itheima.service.impl;

import com.itheima.mongodb.dao.ArticleCommentDao;
import com.itheima.mongodb.domain.ArticleComment;
import com.itheima.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 23:21
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private final ArticleCommentDao articleCommentDao;

    public ArticleCommentServiceImpl(ArticleCommentDao articleCommentDao) {
        this.articleCommentDao = articleCommentDao;
    }

    @Override
    public void saveComment(ArticleComment comment) {
        articleCommentDao.save(comment);
    }

    @Override
    public void deleteByCid(String cid) {
        articleCommentDao.deleteById(cid);
    }

    @Override
    public List<ArticleComment> findCommentsByAid(String aid) {
        return articleCommentDao.findByArticleId(aid);
    }
}
