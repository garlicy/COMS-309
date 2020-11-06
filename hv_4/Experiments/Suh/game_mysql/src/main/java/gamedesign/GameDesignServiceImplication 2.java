package com.example.gamedesign;

import com.example.model.User;
import com.example.register.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class GameDesignServiceImplication implements GameDesignService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setPassword(user.getPassword());
        userRepository.save(user);
    }
    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    
}
