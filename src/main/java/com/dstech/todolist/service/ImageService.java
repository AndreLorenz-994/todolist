package com.dstech.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dstech.todolist.model.User;
import com.dstech.todolist.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public User getFile(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> null);
    }
}
