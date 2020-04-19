package com.itheima.mongodb.dao;

import com.itheima.mongodb.domain.ArticleComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:20
 */
public interface ArticleCommentDao extends MongoRepository<ArticleComment, String> {

    /**
     * 根据文章id查找对应的评论
     *
     * @param articleId 文章id
     * @return List<ArticleComment>
     */
    List<ArticleComment> findByArticleId(String articleId);
}
