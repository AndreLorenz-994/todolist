package com.dstech.todolist.controller;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.TimeZone;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dstech.todolist.model.Activity;
import com.dstech.todolist.model.MyRunnable;
import com.dstech.todolist.model.User;
import com.dstech.todolist.service.ActivityService;
import com.dstech.todolist.service.MailService;
import com.dstech.todolist.service.UserService;

@EnableScheduling
@Controller
public class WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private TaskScheduler scheduler;
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = {"/user/home"}, method=RequestMethod.GET)
    public String userIndex(Model model, Activity activity) throws MessagingException {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findByEmail(auth.getName());   	    
	    List<Activity> activities = user.getActivities();	    
	    model.addAttribute("authUser", user.getEmail());
	    model.addAttribute("authUserImage", Base64.getEncoder().encodeToString(user.getImage()));
        model.addAttribute("activities", activities);
        model.addAttribute("activity", new Activity());
        model.addAttribute("title", "Activities");    		    
        return "user/index";
    }
    
    @PostMapping(value="/save")
    public String save (@ModelAttribute Activity activity, RedirectAttributes redirectAttributes, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findByEmail(auth.getName());   	
	    activity.setUser(user);
        Activity currActivity = activityService.save(activity);
        List<Activity> activities = user.getActivities();
        activities.add(currActivity);
        userService.addActivities(user, activities);
        if(currActivity != null) {
            LocalDateTime date = currActivity.getExpiredDate();
			int minute = date.getMinute();
			int hours = date.getHour();
			int day = date.getDayOfMonth();
			int month = date.getMonth().getValue();
			String expression = " 0 " + (minute - 2) + " " + hours + " " + day + " " + month + " ?";
			CronTrigger trigger = new CronTrigger(expression, TimeZone.getTimeZone(TimeZone.getDefault().getID()));
			MyRunnable myRunnable = new MyRunnable(currActivity, mailService);
            redirectAttributes.addFlashAttribute("successmessage", "Activity is saved successfully");
            scheduler.schedule(myRunnable, trigger);
            return "redirect:/user/home";
        }else {
            model.addAttribute("errormessage", "Activity is not save, Please try again");
            model.addAttribute("activity", activity);
            return "user/index";
        }
    }
}
