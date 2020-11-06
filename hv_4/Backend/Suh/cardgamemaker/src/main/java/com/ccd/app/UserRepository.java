package com.ccd.app;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ccd.app.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
/**
 * UserRepository is extended from CrudRepository
 * @author jsuh_mac
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	User getUserById(Integer id);

//	List<User> getUserList();
}
