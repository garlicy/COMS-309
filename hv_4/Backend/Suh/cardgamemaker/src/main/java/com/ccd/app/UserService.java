package com.ccd.app;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ccd.app.model.User;

/**
 * User Service is a list of fuctions inside User
 * @author jsuh_mac
 *
 */
@Service
public interface UserService {
    void save(User user);
    
    User findByUsername(String username);

    User getUserById(Integer id);

//	List<User> getUserList();
}