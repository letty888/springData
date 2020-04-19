package com.itheima.es.dao;

import com.itheima.es.domain.EsArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:16
 */
public interface EsArticleDao extends ElasticsearchRepository<EsArticle, String> {

    /**
     * 条件分页查询索引库
     *
     * @param title    查询条件: 文章的内容
     * @param content  查询条件: 文章的内容
     * @param pageable 分页参数
     * @return List<EsArticle>
     */
    List<EsArticle> findByTitleLikeOrContentLike(String title, String content, Pageable pageable);
}
