
package com.ucm.ms.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ucm.ms.entity.User;

/**
 * User details/info to be used in AuthController Class to return user details
 * along with the new generated jwt token as a "jwtResponse",
 * and also used in JwtUtil class to generate token
 * 
 * @author Charvin Patel
 */

public class UserInfo implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private String email;
	
	private String phNum;
	
	private String firstName;
	
	private String lastName;

	private String password;
	
	private String address;

	private String city;
	
	private String state;
	
	private String zipcode;

	
	// For future Sprints for Authorities
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UserInfo(int id, String username, String email, String password, String phNum, String firstName, String lastName
			,String address, String city, String state, String zipcode) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phNum = phNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public static UserInfo build(User user) {
		
		return new UserInfo(
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getPhNum(),
				user.getFirstName(),
				user.getLastName(),
				user.getAddress(),
				user.getCity(),
				user.getState(),
				user.getZipcode());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPhNum() {
		return phNum;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserInfo user = (UserInfo) o;
		return Objects.equals(id, user.id);
	}
}
