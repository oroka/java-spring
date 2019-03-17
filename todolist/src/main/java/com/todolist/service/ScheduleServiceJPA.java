package com.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todolist.domain.ScheduleDomain;
import com.todolist.mapper.ScheduleMapperJPA;

@Service
public class ScheduleServiceJPA {

	@Autowired
	ScheduleMapperJPA scheduleMapper;
	
    public Page<ScheduleDomain> findAll(Pageable page){
    	//scheduleMapper.
    	return scheduleMapper.findAll(page);
    };
    
    public void save(ScheduleDomain schedule) {
    	scheduleMapper.save(schedule);
    }
    
    public ScheduleDomain selectOneById(Long id) {
    	return scheduleMapper.getOne(id);
    }
}
