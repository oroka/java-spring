package com.todolist.service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> findOne(Long id) {
        return userMapper.findById(id);
    }

    @Transactional
    public void save(User user) {
        userMapper.save(user);
    }

    @Transactional
    public void update(User user) {
    	userMapper.updateNameById(user.getId(), user.getName());
        //userMapper.update(User.getId(), User.getName(), User.getPassword(), User.getEmail(), User.getAdmin());
    }

    @Transactional
    public void delete(Long id) {
        userMapper.deleteById(id);
    }
    
}
