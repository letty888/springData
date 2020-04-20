package com.itheima.service;

import com.itheima.domain.User;
import com.itheima.result.UserResult;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 23:01
 */
public interface UserService {

    void save(User user);

    void deleteById(String id);

    void update(User user);

    UserResult findAll(String userId);

    User findUser(String userId);
}
