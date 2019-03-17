package com.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todolist.domain.UserDomain;
import com.todolist.service.UserServiceJPA;

@Controller
@RequestMapping("signUp")
public class SignUpController {
	
	@Autowired
	UserServiceJPA userService;

	@GetMapping
	public String index(UserDomain userDomain, Model model) {
		String confpassword = "";
		model.addAttribute("confpassword", confpassword);
		return "signUp";
	}
	
	@PostMapping
	public String confirm(@Validated @ModelAttribute UserDomain user, BindingResult result, @RequestParam String confpassword, Model model) {
		
		System.out.println("confpassword : " + confpassword);
		if(result.hasErrors()) {
			model.addAttribute("validationError", "不正な値が入力されました");
			return index(user, model);
		}
		if(!user.getPassword().equals(confpassword)) {
			model.addAttribute("validationError", "パスワードが一致しません");
			return index(user, model);
		}
		return "signUpConfirm";
	}
	
	@PostMapping("register")
	public String save(@Validated UserDomain user, BindingResult result, Model model) {
		System.out.println("SignUpController - save");
		//SignUpFormの全てのフィールドに値を入れてないとエラーが出る。
		if(result.hasErrors()) {
			System.out.println("Error Count : " + result.getErrorCount());
			for(ObjectError e : result.getAllErrors()) System.out.println("Error : " + e);
			model.addAttribute("validationError", "登録できませんでした。");
			return "signup";
		}
		userService.save(UserDomain.of(user.getName(), user.getPassword(), user.getEmail()));
		return "loginForm";
	}
}
