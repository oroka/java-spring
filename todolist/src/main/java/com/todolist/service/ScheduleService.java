package com.todolist.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.domain.ScheduleDomain;
import com.todolist.mapper.ScheduleMapper;

@Service
public class ScheduleService {
	@Autowired
	ScheduleMapper scheduleMapper;
	
	public List<ScheduleDomain> selectAll() {
		return scheduleMapper.selectAll();
	}
	
	public List<ScheduleDomain> selectSomeByEmail(String email){
		return scheduleMapper.selectSomeByEmail(email);
	}
	
	public List<ScheduleDomain> selectSomeByName(String name){
		return scheduleMapper.selectSomeByName(name);
	}
	
	public List<ScheduleDomain> selectSomeHaveKeyword(String keyword){
		return scheduleMapper.selectSomeHaveKeyword(keyword);
	}
	
	public List<ScheduleDomain> selectSomeByTime(Timestamp time){
		return scheduleMapper.selectSomeByTime(time);
	}
	
	public Boolean insert(String name, String email, String text, String start_time, String end_time) {
		
		//String -> Timestamp 前動いたやつ途中で印刷したのがある
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
			Timestamp ts_start_time = new Timestamp(dateFormat.parse(start_time).getTime());
			Timestamp ts_end_time = new Timestamp(dateFormat.parse(end_time).getTime());
			scheduleMapper.insert(name, email, text, ts_start_time, ts_end_time);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public void deleteOneById(Long id) {
		scheduleMapper.deleteOneById(id);
	}
	
	public void deleteSomeByEmail(String email) {
		scheduleMapper.deleteSomeByEmail(email);
	}
}
