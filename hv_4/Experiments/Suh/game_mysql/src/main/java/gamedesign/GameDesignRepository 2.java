package com.example.gamedesign;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface GameDesignRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}
