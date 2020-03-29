package com.dstech.todolist.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Activity implements Runnable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_id")	
	private Long id;
	
	private String activityTitle;
	
	private String type;
	
	private String description;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime expiredDate;
	
	private boolean isCarryOut;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDateTime expiredDate) {
		this.expiredDate = expiredDate;
	}

	public boolean isCarryOut() {
		return isCarryOut;
	}

	public void setCarryOut(boolean isCarryOut) {
		this.isCarryOut = isCarryOut;
	}

	@Override
	public void run() {
		System.out.println("prova");
	}
}
