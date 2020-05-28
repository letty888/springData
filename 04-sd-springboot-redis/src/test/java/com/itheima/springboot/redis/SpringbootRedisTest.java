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
import java.util.concurrent.TimeUnit;

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


    @Test
    public void testHashPuT() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        redisTemplate.boundHashOps("cart_zhang").put("1", list);

    }

    @Test
    public void testHasKey() {

        List list = redisTemplate.boundHashOps("cart_heima").values();
        Map map = redisTemplate.boundHashOps("cart_heima").entries();
        map.forEach((k, v) -> System.out.println(k + "======" + v));
        System.out.println(list);
    }

    /**
     * redis中没有对应的可以的时候,直接操作这个可以,看看会有什么变化
     */
    @Test
    public void testNoKeyOperator() {

        String redis_key = "seckill_user_" + "zhangwuji" + "_id_" + "888";
        Long count = redisTemplate.boundValueOps(redis_key).increment(1);

        System.out.println(count);
    }
}
