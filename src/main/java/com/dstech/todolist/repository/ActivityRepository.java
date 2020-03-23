package com.dstech.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dstech.todolist.model.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

}
