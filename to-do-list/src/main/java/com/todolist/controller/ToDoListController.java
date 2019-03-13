package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.service.UserService;

@Controller
@RequestMapping("todolist")
public class ToDoListController {
	
	
	@GetMapping
	public String index(Model model) {		
		return "todolist";		
	}
	
}
