package com.todolist.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.domain.ItemDto;
import com.todolist.security.ToDoListLoginUser;

@Controller
@RequestMapping
public class HelloController {
	
	@GetMapping(path = {"", "hello"})
    public String greeting(Model model/*, Principal principal*/) {
		
		//System.out.println("principal : " + principal);
		/*if(principal != null) {
			Authentication auth = (Authentication)principal;
			ToDoListLoginUser user = (ToDoListLoginUser)auth.getPrincipal();
			model.addAttribute("userLoginFlag", user.getUser());
		}*/
		
        model.addAttribute("message", "HELLO WORLD");
        model.addAttribute("date", LocalDateTime.of(2018, 4, 13, 11, 12, 13));
        List<ItemDto> items = Arrays.asList(
                ItemDto.of(1L, "うまい棒", 10, LocalDateTime.now()),
                ItemDto.of(2L, "ポテトフライ", 30, LocalDateTime.now()),
                ItemDto.of(3L, "きなこ棒", 10, LocalDateTime.now()),
                ItemDto.of(4L, "いきいきビール", 40, LocalDateTime.now())
        );
        model.addAttribute("items", items);
        return "hello";
    }

    @GetMapping(path = "checkout")
    public String checkout() {
        return "checkout";
    }
	
}
