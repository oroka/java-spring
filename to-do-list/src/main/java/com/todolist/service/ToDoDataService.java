package com.todolist.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.domain.ToDoData;
import com.todolist.mapper.ToDoDataMapper;

@Service
public class ToDoDataService {

	@Autowired
	ToDoDataMapper toDoDataMapper;
	
	@Transactional
	public List<ToDoData> findAll() {
		return toDoDataMapper.findAll();
	}
	
	@Transactional
	public ToDoData findOne(Long id) {
		return toDoDataMapper.findOne(id);
	}
	
	@Transactional
	public List<ToDoData> findByUserName(String username) {
		return toDoDataMapper.findByUserName(username);
	}
	
	@Transactional
	public List<ToDoData> findBySelectTime(Timestamp select_time) {
		return toDoDataMapper.findBySelectTime(select_time);
	}
	
	@Transactional
	public void insert(String username, Timestamp start_time, Timestamp end_time, String to_do) {
		toDoDataMapper.insert(username, start_time, end_time, to_do);
	}
	
	@Transactional
	public void update(ToDoData to_do_data) {
		toDoDataMapper.update(to_do_data);
	}
	
	@Transactional
	public void delete(Long id) {
		toDoDataMapper.delete(id);
	}
	
	@Transactional
	public void deleteAll() {
		toDoDataMapper.deleteAll();
	}
}
