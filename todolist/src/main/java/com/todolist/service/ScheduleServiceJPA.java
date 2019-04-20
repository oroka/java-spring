package com.todolist.service;

import java.util.Iterator;
import java.util.Optional;
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
    	//scheduleMapper
    	return scheduleMapper.findAll(page);
    }
    
    public Page<ScheduleDomain> findPageHavingWord(Pageable page, Optional<String> word){
    	Page<ScheduleDomain> tPage = scheduleMapper.findAll(page);
    	System.out.println(tPage);
    	System.out.println("ScheduleServiceJPA - findPageHavingWord");
    	tPage.forEach(System.out::println);
    	System.out.println("ScheduleServiceJPA - findPageHavingWord : end");
    	Iterator<ScheduleDomain> it = tPage.iterator();
    	if(word.isPresent())
    	while(it.hasNext()) {
    		if(!it.next().getDescription().contains(word.get())) {
    			System.out.println("element not contains " + word.get() + " is removed in iterator");
    			it.remove();
    		}
    	}
    	//　こういうところを勉強しよう
    	System.out.println(tPage);
    	
    	//List<ScheduleDomain> lsd = tPage.stream().filter(s->s.getDescription().contains(word)).collect(Collectors.toList());
    	return tPage;
    }
    
    public void save(ScheduleDomain schedule) {
    	scheduleMapper.save(schedule);
    }
    
    public ScheduleDomain selectOneById(Long id) {
    	return scheduleMapper.getOne(id);
    }
}
