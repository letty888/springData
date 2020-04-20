package com.itheima.service.impl;


import com.itheima.dao.OrderDao;
import com.itheima.dao.UserDao;
import com.itheima.domain.Order;
import com.itheima.domain.User;
import com.itheima.result.UserResult;
import com.itheima.service.OrderService;
import com.itheima.service.UserService;
import com.itheima.utils.IdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 23:07
 */

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final IdWorker idWorker;


    public OrderServiceImpl(OrderDao orderDao, IdWorker idWorker) {
        this.orderDao = orderDao;
        this.idWorker = idWorker;
    }

    @Override
    public void save(Order order) {

        orderDao.save(order);
    }

    @Override
    public void deleteById(String id) {
        Optional<Order> orderOptional = orderDao.findById(id);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("该数据不存在!");
        }
        orderDao.deleteById(id);
    }

    @Override
    public void update(Order order) {
        Optional<Order> orderOptional = orderDao.findById(order.getId());
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("该数据不存在!");
        }
        Order orderResult = orderOptional.get();
        BeanUtils.copyProperties(order, orderResult);
        orderDao.save(orderResult);
    }


}
