package com.itheima.es.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:11
 */
@Document(indexName = "case", type = "article")
@Getter
@Setter
@ToString
public class EsArticle {

    @Id
    @Field(type = FieldType.Integer)
    private String id;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store = true)
    private String title;

    /**
     * 内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store = true)
    private String content;


    /**
     * 作者
     */
    @Field(type = FieldType.Text)
    private String author;


    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;
}
