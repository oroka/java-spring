package com.todolist.controller;

import java.security.Principal;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerAdviceImpl {

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
	
	/*
	 * userLoginFlag : ログイン状態の保持
	 */
	@ModelAttribute("userLoginFlag")
    public void addOneObject(Principal principal, Model model) {
        // Modelに追加する
		/*if(principal != null) {
			Authentication auth = (Authentication)principal;
			ToDoListLoginUser user = (ToDoListLoginUser)auth.getPrincipal();
			model.addAttribute("userLoginFlag", user.getUser());
		}*/
    }
	
}
