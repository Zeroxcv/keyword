package com.lisk.keyword.service;


import com.lisk.keyword.pojo.User;

public interface UserService {
    /**通过账号查询用户信息*/
    public User FindUserLogin(String username);
}
