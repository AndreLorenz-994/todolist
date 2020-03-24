package com.dstech.todolist.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dstech.todolist.model.User;
import com.dstech.todolist.service.UserService;



@Controller
public class WebController {
	
	@Autowired
	private UserService userService;	

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user/home")
    public String userIndex(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findByEmail(auth.getName());   	
	    model.addAttribute("authUser", user.getEmail());
	    model.addAttribute("authUserImage", Base64.getEncoder().encodeToString(user.getImage()));
        return "user/index";
    }
	
}
