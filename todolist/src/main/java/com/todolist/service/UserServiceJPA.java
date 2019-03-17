package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todolist.domain.UserDomain;
import com.todolist.mapper.UserMapperJPA;

@Service
public class UserServiceJPA {
	@Autowired
	UserMapperJPA userMapper;
	
	public Page<UserDomain> findAll(Pageable page){
		
		return userMapper.findAll(page);
	}
}
