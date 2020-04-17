package com.itheima.springboot.redis.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/17 17:28
 */
public class User implements Serializable {
    private String id;
    private String username;
    private String address;
    private Date birthday;

    public User() {
    }

    public User(String id, String username, String address, Date birthday) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
