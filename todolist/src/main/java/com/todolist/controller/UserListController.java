package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.domain.UserDomain;
import com.todolist.service.UserServiceJPA;

@Controller
@RequestMapping("userlist")
public class UserListController {
	@Autowired
	UserServiceJPA userService;
	
	@GetMapping
	public String index(Pageable page, Model model) {
		Page<UserDomain> users = userService.findAll(page);
		model.addAttribute("users", users);
		
		return "userlist";
	}
	
	//　登録・検索・削除
}
