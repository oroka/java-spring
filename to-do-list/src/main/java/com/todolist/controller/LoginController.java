package com.todolist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.domain.LoginForm;
import com.todolist.domain.User;
import com.todolist.security.ToDoListLoginUser;

@Controller
@RequestMapping("loginForm")
public class LoginController {
	
	@GetMapping
	public String index(Model model, HttpServletRequest request,User user) {// 参照渡し
		
		return "loginForm";		
	}
	
	/*@PostMapping
	public void confirm(Model model, HttpServletRequest request, HttpServletResponse response, LoginForm loginForm) throws IOException, ServletException {
		System.out.println("loginForm - name : " + loginForm.getName());
		System.out.println("loginForm - url) : " + loginForm.getPrePageUrl());
		
		System.out.println("request - getContextPath : " + request.getContextPath());
		System.out.println("request - getContextType : " + request.getContentType());
		System.out.println("request - getRequestURI : " + request.getRequestURI());
		
		response.setHeader("Referer", loginForm.getPrePageUrl());
		System.out.println("request.getContextPath() + \"login\" : " + request.getContextPath() + "login");
		response.sendRedirect(request.getContextPath() + "login");
	}*/
	
}