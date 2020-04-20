package com.itheima.test;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.Order;
import com.itheima.domain.User;
import com.itheima.result.UserResult;
import com.itheima.service.OrderService;
import com.itheima.service.UserService;
import com.itheima.utils.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 23:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTest {


    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private IdWorker idWorker;

    @Test
    public void testSave() throws Exception {
        User user = new User();
        String userId = idWorker.nextId() + "";
        user.setId(userId);
        user.setAge(38);
        user.setAddress("深圳3");
        user.setUsername("高级管理员3");

        Order order1 = new Order();
        String order1Id = idWorker.nextId() + "";
        order1.setId(order1Id);
        order1.setCreateTime(new Date());
        order1.setFea(BigDecimal.valueOf(10000L));

        Thread.sleep(10);

        Order order2 = new Order();
        String orderId2 = idWorker.nextId() + "";
        order2.setId(orderId2);
        order2.setCreateTime(new Date());
        order2.setFea(BigDecimal.valueOf(20000L));

        //设置两者关系
        Set<Order> orders = new HashSet<>(0);
        orders.add(order1);
        orders.add(order2);
        user.setOrders(orders);

        order1.setUser(user);
        order2.setUser(user);

        userService.save(user);
        orderService.save(order1);
        orderService.save(order2);
    }

    @Test
    public void testDelete() {
        String userId = "1252264771034648576";
        userService.deleteById(userId);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId("1252259335896084480");
        user.setAge(33);
        user.setAddress("上海");
        user.setUsername("初级管理员");
        userService.update(user);
    }

    @Test
    public void testFindAll() {
        UserResult userResultList = userService.findAll("1252259335896084480");
        System.out.println(userResultList);
    }

    @Test
    public void testFindUser(){
        User user = userService.findUser("1252259335896084480");
        System.out.println(JSON.toJSONString(user));
    }
}
