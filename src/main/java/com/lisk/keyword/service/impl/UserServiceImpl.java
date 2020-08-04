package com.lisk.keyword.service.impl;

import com.lisk.keyword.mapper.UserMapper;
import com.lisk.keyword.pojo.User;
import com.lisk.keyword.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User FindUserLogin(String username) {
        return userMapper.FindUserLogin(username);
    }
}
