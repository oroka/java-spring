package com.todolist.service;

import com.todolist.domain.Memo;
import com.todolist.mapper.MemoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemoService {
	
	@Autowired
	MemoMapper memoMapper;
	
    public Page<Memo> findAll(Pageable page){
    	//memoMapper.
    	return memoMapper.findAll(page);
    };
}
