package com.lisk.keyword.repository;

import com.lisk.keyword.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class UserRepositoryImpl implements UserRepository{

    private static AtomicLong counterId = new AtomicLong();

    private final ConcurrentHashMap<Long,User> userConcurrentHashMap = new ConcurrentHashMap<Long,User>();

    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if(id==null){
            id = counterId.incrementAndGet();
            user.setId(id);
        }
        this.userConcurrentHashMap.put(id,user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        this.userConcurrentHashMap.remove(id);
    }

    @Override
    public User getUserById(Long id) {

        return this.userConcurrentHashMap.get(id);
    }

    @Override
    public List<User> userList() {
        return new ArrayList<User>(this.userConcurrentHashMap.values());
    }
}
