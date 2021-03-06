package com.dstech.todolist.service;

import java.util.List;

import com.dstech.todolist.model.Activity;

public interface ActivityService {

    Activity save(Activity activity);
    Activity findByID(Long id);
    void delete(Long id);
	List<Activity> getAllActivities();	
    
}
