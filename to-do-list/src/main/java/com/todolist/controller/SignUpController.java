package com.todolist.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.domain.SignUpForm;
import com.todolist.domain.User;
import com.todolist.service.UserService;

@Controller
@RequestMapping("signup")
public class SignUpController {
	
	@Autowired
	UserService userService;

	@GetMapping
	public String input(User user, Model model) {
		
		
		return "signUp";
	}
	
	@PostMapping
	public String conform(@Validated @ModelAttribute User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("validationError", "不正な値が入力されました");
			return input(user, model);
		}
		if(!user.getPassword().equals(user.getConfpassword())) {
			model.addAttribute("validationError", "パスワードが一致しません");
			return input(user, model);
		}
		return "signUpConfirm";
	}
	
	@PostMapping("register")
	public String save(@Validated User user, BindingResult result, Model model) {
		//System.out.println("form.getNickname : " + form.getNickname());
		//System.out.println("form.getPassword * " + form.getPassword());
		
		//SignUpFormの全てのフィールドに値を入れてないとエラーが出る。
		if(result.hasErrors()) {
			System.out.println("Error Count : " + result.getErrorCount());
			for(ObjectError e : result.getAllErrors()) System.out.println("Error : " + e);
			model.addAttribute("validationError", "登録できませんでした。");
			return "signup";
		}
		userService.save(User.of(user.getName(), user.getPassword(), user.getEmail()));
		return "loginForm";
	}
}
