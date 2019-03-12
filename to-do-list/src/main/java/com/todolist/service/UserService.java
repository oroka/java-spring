package com.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.domain.User;
import com.todolist.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
    private UserMapper userMapper;

    @Transactional
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Transactional
    public User findOne(Long id) {
        return userMapper.findOne(id);
    }

    @Transactional
    public void save(User User) {
        userMapper.save(User);
    }

    @Transactional
    public void update(User User) {
        userMapper.update(User);
    }

    @Transactional
    public void delete(Long id) {
        userMapper.delete(id);
    }
    
}
