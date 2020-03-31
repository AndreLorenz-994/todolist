package com.dstech.todolist.model;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.dstech.todolist.service.MailService;

public class MyRunnable implements Runnable {
	
	@Autowired
	MailService mail;
	
	private Activity activity;
	
	public MyRunnable(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		User currUser = activity.getUser();
		String email = currUser.getEmail();
		try {
			mail.sendMail(currUser.getEmail(), "test", "test test");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
