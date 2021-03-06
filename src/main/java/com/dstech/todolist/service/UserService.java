package com.dstech.todolist.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dstech.todolist.dao.UserRegistrationDao;
import com.dstech.todolist.model.User;
import com.dstech.todolist.model.Activity;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDao registration);
    
    Long count();
    
    void deleteById(Long userId);
    
    void addActivities(User user, Activity activities);
	
}
