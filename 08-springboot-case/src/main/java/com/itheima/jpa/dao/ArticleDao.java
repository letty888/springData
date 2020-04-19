package com.itheima.jpa.dao;

import com.itheima.jpa.domain.Article;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:08
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
}
