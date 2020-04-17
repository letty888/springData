package com.itheima.springboot.redis;

import com.itheima.springboot.redis.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/17 17:33
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test01() {
        // redisTemplate.boundValueOps("name").set("itheima",10, TimeUnit.SECONDS);

      /*  Map<String,Object> map = new HashMap<>();
        User user1 = new User("1", "张三丰", "武当山", new Date());
        User user3 = new User("3", "张无忌", "光明顶", new Date());
        User user2 = new User("2", "风清扬", "华山后院", new Date());
        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);
        map.put(user3.getId(), user3);
        redisTemplate.boundHashOps("user").putAll(map);*/


        Set<String> s = redisTemplate.opsForHash().keys("user");
        System.out.println("key有: ");
        s.forEach(System.out::println);
        System.out.println("====================");
        List<User> user = redisTemplate.opsForHash().values("user");
        user.forEach(System.out::println);
        System.out.println("====================");
        Map<String, User> map = redisTemplate.opsForHash().entries("user");
        for (Map.Entry<String, User> stringUserEntry : map.entrySet()) {
            System.out.println(stringUserEntry.getKey() + ":" + stringUserEntry.getValue());
        }
    }
}
