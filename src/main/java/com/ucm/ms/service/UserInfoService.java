package com.ucm.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserInfo.build(user);
	}

}
