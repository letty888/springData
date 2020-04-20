package com.itheima.dao;

import com.itheima.domain.User;
import com.itheima.result.UserResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @date 2020/4/20 23:00
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


}
