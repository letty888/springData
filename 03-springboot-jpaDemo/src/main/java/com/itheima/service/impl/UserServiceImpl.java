package com.itheima.service.impl;


import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.result.UserResult;
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
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final IdWorker idWorker;


    public UserServiceImpl(UserDao userDao, IdWorker idWorker) {
        this.userDao = userDao;
        this.idWorker = idWorker;
    }

    @Override
    public void save(User user) {

        userDao.save(user);
    }

    @Override
    public void deleteById(String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("该数据不存在!");
        }
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        Optional<User> userOptional = userDao.findById(user.getId());
        if (!userOptional.isPresent()) {
            throw new RuntimeException("该数据不存在!");
        }
        User userResult = userOptional.get();
        BeanUtils.copyProperties(user, userResult);
        userDao.save(userResult);
    }

    @Override
    public UserResult findAll(String userId) {
        User user = userDao.findById(userId).get();
        return new UserResult(user);


    }

    @Override
    public User findUser(String userId) {
        return userDao.findById(userId).get();
    }


}
