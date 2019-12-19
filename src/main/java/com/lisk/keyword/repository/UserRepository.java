package com.lisk.keyword.repository;

import com.lisk.keyword.pojo.User;

import java.util.List;

public interface UserRepository {
    User saveOrUpdateUser(User user);//新增或者修改用户
    void deleteUser(Long id);//删除用户
    User getUserById(Long id); //通过用户id获取User
    List<User> userList();//获取所有用户的列表
}
