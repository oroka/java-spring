package com.todolist.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

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
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") String id, Pageable page, Model model, UriComponentsBuilder builder) {
		Page<UserDomain> users = userService.findAll(page);
		model.addAttribute("users", users);
		
		UserDomain ud = userService.findOneById(Long.parseLong(id));
		userService.delete(ud);
		
		URI location = builder.path("/logout").build().toUri();
		
		return "redirect:" + location.toString();
	}
}
