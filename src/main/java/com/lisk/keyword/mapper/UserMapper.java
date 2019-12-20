package com.lisk.keyword.mapper;

import com.lisk.keyword.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> list();
}
