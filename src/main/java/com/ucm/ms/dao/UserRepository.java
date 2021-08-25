package com.ucm.ms.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ucm.ms.entity.User;

/**
 * User Repository/ DAO Layer 
 * for interacting with the database
 * 
 * @author Charvin Patel
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
	
	public User findByEmailIgnoreCase(String email);
	
	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);
	
	public User findById(int id);
	
	public User getUserById(int id);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE User u SET u.username = :username, u.email = :email, u.password = :password, u.phNum = :phNum, u.firstName = :firstName, u.lastName = :lastName, u.address = :address, u.city = :city, u.state = :state, u.zipcode = :zipcode, u.isActive = :isActive where u.id = :userId")
//	public void updateUser(@Param("userId") int userId, @Param("username") String username, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email, @Param("password") String password, @Param("phNum") String phNum, @Param("address") String address, @Param("city") String city, @Param("state") String state, @Param("zipcode") String zipcode, @Param("isActive") Boolean isActive );
	
	
		

}