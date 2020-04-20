package com.itheima.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 22:31
 */
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 5174355104771459464L;

    @Id
    private String id;
    private String username;
    private String address;
    private Integer age;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private Set<Order> orders = new HashSet<>(0);

}
