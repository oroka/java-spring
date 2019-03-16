package com.todolist.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import com.todolist.domain.User;
import com.todolist.security.ToDoListLoginUser;

@ControllerAdvice
public class ToDoListControllerAdvice {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	@ModelAttribute("userLoginFlag")
    public void addOneObject(/*@RequestHeader("Referer") Optional<String> referer*/Principal principal, Model model) {
        // Modelに追加する
		if(principal != null) {
			Authentication auth = (Authentication)principal;
			ToDoListLoginUser user = (ToDoListLoginUser)auth.getPrincipal();
			model.addAttribute("userLoginFlag", user.getUser());
		}
    }
	
	@ModelAttribute("prePageUrl")
    public void addOneObject2(User user, HttpServletRequest request, Model model) {//　ここでコンストラクタ呼ばれる感じかな（引数はフォームからとる） リダイレクトでフォームが空になる
		System.out.println("addOneObject2 is called : referer : " + request.getHeader("Referer"));
        if(user != null) {
			System.out.println("user - name : " + user.getName());
			System.out.println("user - email : " + user.getEmail());
		}
    }
	
}
