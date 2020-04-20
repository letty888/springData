package com.itheima.dao;

import com.itheima.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 23:00
 */
public interface OrderDao extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}
