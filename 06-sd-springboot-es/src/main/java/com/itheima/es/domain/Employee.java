package com.itheima.es.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 10:01
 */
@Document(indexName = "es-demo", type = "employee")
@Data
public class Employee {
    /**
     * index：是否设置分词  默认是true
     * analyzer：存储时使用的分词器
     * searchAnalyze：搜索时使用的分词器
     * store：是否存储 默认是false
     * type: 数据类型 默认值是FieldType.Auto
     */
    @Id
    @Field(index = false, type = FieldType.Text)
    private String id;

    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", store = true, type = FieldType.Text)
    private String name;

    @Field(store = true, type = FieldType.Integer)
    private Integer age;

    @Field(analyzer = "ik_smart", searchAnalyzer = "ik_smart", store = true, type = FieldType.Text)
    private String address;


}
