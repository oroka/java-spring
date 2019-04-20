package com.todolist.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

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
		//Page<ScheduleDomain> schedules = scheduleService.findPageHavingWord(page, "Scanner");
		model.addAttribute("schedules", schedules);
		model.addAttribute("scheduleDomain", new ScheduleDomain());
		
		return "schedule";
	}
	
	@PostMapping("search")
	public String search(Pageable page, Model model) {
		
		return "schedule";
	}
	
	@GetMapping("search")
	public String search(Pageable page, Model model, @RequestParam("word") Optional<String> word, UriComponentsBuilder builder) {
		System.out.println("ScheduleController - search : start");
		System.out.println("String - word : " + word);
		Page<ScheduleDomain> schedules = scheduleService.findPageHavingWord(page, word);
		model.addAttribute("schedules", schedules);
		model.addAttribute("scheduleDomain", new ScheduleDomain());
		
		System.out.println("ScheduleController - search : end");
		
		return "schedule";//template
	}
	
	@GetMapping("detail/{id}")
	public String detail(@PathVariable("id") String id, Model model) {
		ScheduleDomain schedule = scheduleService.selectOneById(Long.parseLong(id));
		model.addAttribute("schedule", schedule);
		model.addAttribute("now_time", LocalDateTime.now());
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
