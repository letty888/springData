package com.itheima.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 16:00
 */
@Entity
@Table(name = "tb_article")
@Getter
@Setter
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = -6285131943605844031L;
    @Id
    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 文章内容
     */
    private String content;

}
