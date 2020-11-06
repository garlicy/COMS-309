package com.ccd.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccd.app.UserRepository;
import com.ccd.app.model.User;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Implication of User Service
 * @author jsuh_mac
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Saving User informations in Database
     */
    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    	user.setPassword(user.getPassword());
        userRepository.save(user);
    }
    
    /**
     * Find by username 
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    /**
     * Find User by its primary key, id
     */
    @Override
    public User getUserById(Integer id) {
    	return userRepository.getUserById(id);
    }

    /**
     * get the Users by List
     */
//	@Override
//	public List<User> getUserList() {
//		return userRepository.findAll();
//	}
}
