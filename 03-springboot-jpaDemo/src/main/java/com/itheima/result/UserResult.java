package com.itheima.result;

import com.itheima.domain.Order;
import com.itheima.domain.User;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 23:19
 */
@ToString
public class UserResult implements Serializable {
    private static final long serialVersionUID = -9098683866346051957L;

    private String id;
    private String username;
    private String address;
    private Integer age;
    private Set<OrderResult> set = new HashSet<>();


    public UserResult(User user) {
        this.id = user.getId();
        this.address = user.getAddress();
        this.age = user.getAge();
        this.username = user.getUsername();

        Set<Order> orders = user.getOrders();
        for (Order order : orders) {
            OrderResult orderResult = new OrderResult();
            BeanUtils.copyProperties(order, orderResult);
            this.set.add(orderResult);
        }
    }
}
