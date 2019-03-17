package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.domain.UserDomain;
import com.todolist.mapper.UserMapperJPA;

@Service
@Transactional
public class UserServiceJPA {
	@Autowired
	UserMapperJPA userMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Page<UserDomain> findAll(Pageable page){
		
		return userMapper.findAll(page);
	}
	
	public void save(UserDomain user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("UserServiceJPA - save");
		userMapper.save(user);
	}
}
