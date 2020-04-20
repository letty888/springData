package com.itheima.mongodb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:17
 */
@Document(collection = "articleComment")
@Getter
@Setter
@ToString
public class ArticleComment implements Serializable {

    private static final long serialVersionUID = -965418410303247540L;
    @Id
    private String id;

    /**
     * 文章标识
     */
    private Integer articleId;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论者名称
     */
    private String nickName;
}
