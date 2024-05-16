package com.hexaware.recruitment.dto;

import com.hexaware.recruitment.entity.Profile;

import jakarta.persistence.OneToOne;

public class UserDTO {
	private String name;
	private String email;
	private String address;
	private String userType;
	private String profileHeadline;
	private String password;
	
	public UserDTO() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getProfileHeadline() {
		return profileHeadline;
	}
	public void setProfileHeadline(String profileHeadline) {
		this.profileHeadline = profileHeadline;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
