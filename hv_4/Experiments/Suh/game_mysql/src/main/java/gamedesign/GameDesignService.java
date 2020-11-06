package com.example.gamedesign;


import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public interface GameDesignService {
    void save(User user);
    
    User findByUsername(String username);
}