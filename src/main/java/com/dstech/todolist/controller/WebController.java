package com.dstech.todolist.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dstech.todolist.model.Activity;
import com.dstech.todolist.model.User;
import com.dstech.todolist.service.ActivityService;
import com.dstech.todolist.service.UserService;



@Controller
public class WebController {
	
	@Autowired
	private UserService userService;	
	
	@Autowired
	private ActivityService activityService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user/home")
    public String userIndex(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Activity>activities = activityService.getAllActivities();
	    User user = userService.findByEmail(auth.getName());   	
	    model.addAttribute("authUser", user.getEmail());
	    model.addAttribute("authUserImage", Base64.getEncoder().encodeToString(user.getImage()));
        model.addAttribute("activities", activities);
        model.addAttribute("activity", new Activity());
        model.addAttribute("title", "Activities");    		    
        return "user/index";
    }
    
    @PostMapping(value="/user/home")
    public String save (@ModelAttribute Activity activity, RedirectAttributes redirectAttributes, Model model) {
        Activity dbActivity = activityService.save(activity);
        if(dbActivity != null) {
            redirectAttributes.addFlashAttribute("successmessage", "Activity is saved successfully");
            return "user/index";
        }else {
            model.addAttribute("errormessage", "Activity is not save, Please try again");
            model.addAttribute("activity", activity);
            return "user/index";
        }
    }    
	
}
