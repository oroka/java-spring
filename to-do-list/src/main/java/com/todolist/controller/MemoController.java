package com.todolist.controller;

import com.todolist.domain.Memo;
import com.todolist.security.ToDoListLoginUser;
import com.todolist.service.MemoService;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("memo")
public class MemoController {

    final private MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping
    public String list(Pageable page, Model model/*, Principal principal*/) {
    	
    	/*if(principal != null) {
			Authentication auth = (Authentication)principal;
			ToDoListLoginUser user = (ToDoListLoginUser)auth.getPrincipal();
			model.addAttribute("userLoginFlag", user.getUser());
		}*/
    	
        Page<Memo> memos = memoService.findAll(page);
        memos.getTotalPages();
        model.addAttribute("totalPages", memos.getTotalPages());
        model.addAttribute("memos", memos);
        return "memo";
    }

}
