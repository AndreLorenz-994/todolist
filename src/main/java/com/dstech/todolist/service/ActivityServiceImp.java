package com.dstech.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstech.todolist.model.Activity;
import com.dstech.todolist.repository.ActivityRepository;

@Service
public class ActivityServiceImp implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return (List<Activity>) activityRepository.findAll();
	}

	@Override
	public Activity save(Activity activity) {
		// TODO Auto-generated method stub
		return  activityRepository.save(activity);
	}

	@Override
	public Activity findByID(Long id) {
		// TODO Auto-generated method stub
        Optional<Activity> activity = activityRepository.findById(id);
        if(activity.isPresent()) {
            return activity.get();
        } else {
            return null;
        }
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		activityRepository.deleteById(id);
	}

}
