package com.itheima.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/21 0:33
 */
@ToString
@Getter
@Setter
public class OrderResult implements Serializable {
    private static final long serialVersionUID = 3619213710494204743L;
    private String id;
    private Date createTime;
    private BigDecimal fea;
}
