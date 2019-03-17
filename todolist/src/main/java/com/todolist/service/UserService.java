package com.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.domain.UserDomain;
import com.todolist.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<UserDomain> selectAll(){
		return userMapper.selectAll();
	}
	
	public UserDomain selectOneByEmail(String email) {
		return userMapper.selectOneByEmail(email);
	}
	
	public void insert(UserDomain user) {
		userMapper.insert(user.getName(), user.getPassword(), user.getEmail(), user.getAdmin());
	}
	
	public void deleteOneById(Long id) {
		userMapper.deleteOneById(id);
	}
}
