package com.ucm.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucm.ms.dao.UserRepository;
import com.ucm.ms.entity.User;

/**
 * UserInfo Service layer to call a DAO layer for CRUD
 * 
 * @author Charvin Patel
 */


@Service
@Transactional
public class UserInfoService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	/**
	 * 
	 * @param username
	 * @return UserDetails
	 * 
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


		User user = userRepository.findByUsername(username);

		return UserInfo.build(user);
	}


	public User getUser(int id) {
		User user = userRepository.getUserById(id);
		return user;
	}

	
	public void deleteUser(int id) {
		User user = userRepository.getUserById(id);
		
		// We don't actaully delete the user but to set it
		// as "inactive" so they can't login now.
		
		user.setIsActive(false);
	}
	
	public void updateUser(User user) {

//		System.out.println("user info: " + user.getId() + user.getUsername());
		User u = userRepository.findById(user.getId());
//		System.out.println("u info" + u.getId() + u.getUsername());
		u.setId(user.getId());
		u.setUsername(user.getUsername());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setPhNum(user.getPhNum());
		u.setStreet(user.getStreet());
		u.setCity(user.getCity());
		u.setState(user.getState());
		u.setZipcode(user.getZipcode());
		u.setIsActive(user.getisActive());
		
		userRepository.save(u);
		
	}
	

}
