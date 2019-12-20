package com.lisk.keyword.service.impl;

import com.lisk.keyword.mapper.UserMapper;
import com.lisk.keyword.pojo.User;
import com.lisk.keyword.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list(){
        return userMapper.list();
    }
}
