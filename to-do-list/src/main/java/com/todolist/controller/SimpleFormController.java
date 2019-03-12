package com.todolist.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.todolist.domain.SimpleForm;

@Controller
@RequestMapping("simple")
public class SimpleFormController {
	
	final static Map<String, String> SELECT_ITEMS =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
				{
					put("select_A", "A");
					put("select_B", "B");
					put("select_C", "C");
					put("select_D", "D");
					put("select_E", "E");
				}
			});
	
	final static Map<String, String> CHECK_ITEMS =
			Collections.unmodifiableMap(new LinkedHashMap<String, String>(){
				{
					put("checkbox_A", "A");
					put("checkbox_B", "B");
					put("checkbox_C", "C");
					put("checkbox_D", "D");
					put("checkbox_E", "E");
				}
			});
	
	final static Map<String, String> RADIO_ITEMS =
            Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
                {
                    put("radio_A", "A");
                    put("radio_B", "B");
                    put("radio_C", "C");
                    put("radio_D", "D");
                    put("radio_E", "E");
                }
            });
	
	/*
	 * final static Map<String, String> items = 
			Collections.unmodifiableMap(new LinkedHashMap<String, String>(){
				{
					put("", "");
				}
			});
	**/
	
	@GetMapping
	public String input(SimpleForm form, Model model) {
		model.addAttribute("selectItems", SELECT_ITEMS);
		model.addAttribute("checkItems", CHECK_ITEMS);
		model.addAttribute("radioItems", RADIO_ITEMS);
		
		form.setRadio("E");
		form.setMultiSelects(new String[] {"A", "B"});
		form.setMultiChecks(new String[] {"B", "D"});
		
		return "simpleInput";
	}
	
	@PostMapping
	public String conform(@Validated @ModelAttribute SimpleForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("validationError", "不正な値が入力されました");
			return input(form, model);
		}
		return "simpleConfirm";
	}
	
}
