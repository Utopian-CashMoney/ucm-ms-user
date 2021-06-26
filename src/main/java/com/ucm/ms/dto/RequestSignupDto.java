package com.ucm.ms.dto;

import javax.validation.constraints.NotBlank;

/**
 * RequestSignupDto class needed to hold the user signup details
 * the user enters via signup page which then gets added into
 * the database
 * 
 * @author Charvin Patel
 */


public class RequestSignupDto {
	@NotBlank
	private String username;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String phone;

	@NotBlank
	private String first_name;

	@NotBlank
	private String last_name;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

}
