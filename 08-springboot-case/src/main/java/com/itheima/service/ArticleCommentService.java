package com.itheima.service;


import com.itheima.mongodb.domain.ArticleComment;

import java.util.List;

public interface ArticleCommentService {

    /**
     * 保存
     *
     * @param comment 文章评论参数
     */
    void saveComment(ArticleComment comment);

    /**
     * 删除
     *
     * @param cid 评论的id
     */
    void deleteByCid(String cid);

    /**
     * 根据文章标识查询评论数据
     *
     * @param aid 文章id
     * @return List<ArticleComment>
     */
    List<ArticleComment> findCommentsByAid(String aid);
}
