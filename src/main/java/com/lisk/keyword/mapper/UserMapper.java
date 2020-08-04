package com.lisk.keyword.mapper;

import com.lisk.keyword.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User FindUserLogin(String username);
}
