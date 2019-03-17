package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.todolist.domain.ScheduleDomain;
import com.todolist.service.ScheduleServiceJPA;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
	@Autowired
	ScheduleServiceJPA scheduleService;
	
	@GetMapping
	public String index(Pageable page, Model model) {
		Page<ScheduleDomain> schedules = scheduleService.findAll(page);
		model.addAttribute("schedules", schedules);
		model.addAttribute("scheduleDomain", new ScheduleDomain());
		
		return "schedule";
	}
	
	@GetMapping("{id}")
	public String detail(@PathVariable("id") String id, Model model) {
		ScheduleDomain schedule = scheduleService.selectOneById(Long.parseLong(id));
		model.addAttribute("schedule", schedule);
		return "scheduleDetail";
	}
	
	/*
	 * スケジュール登録ページへ
	 */
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("scheduleDomain", new ScheduleDomain());
		return "scheduleRegister";
	}
	
	/*
	 * データベースに登録
	 */
	@PostMapping
	public String save(Model model, ScheduleDomain schedule) {
		schedule.setName("noname");
		schedule.setEmail("sample@email.com");
		
		scheduleService.save(schedule);
		return "redirect:schedule";
	}
	//　登録・検索・削除
}
