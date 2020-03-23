package com.dstech.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dstech.todolist.model.User;

@Repository
public interface ImageRepository extends JpaRepository<User, Long> {

}
