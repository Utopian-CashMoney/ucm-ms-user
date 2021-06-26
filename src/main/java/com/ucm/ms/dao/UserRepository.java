package com.ucm.ms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ucm.ms.entity.User;

/**
 * User Repository/ DAO Layer 
 * for interacting with the database
 * 
 * @author Charvin Patel
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
	
	User findByEmailIgnoreCase(String email);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	User findById(int id);
	
	public User getUserById(int id);

}