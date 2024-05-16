package com.hexaware.recruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class User {
	@Id
	@SequenceGenerator(name = "user_sequence", initialValue = 10001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private long userId;
	private String name;
	private String email;
	private String address;
	private String userType;
	private String profileHeadline;
	private String password;
	@OneToOne
	private Profile profile;
	
	public User() {
		
	}
	public User(long userId, String name, String email, String address, String userType, String profileHeadline,
			String password, Profile profile) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.address = address;
		this.userType = userType;
		this.profileHeadline = profileHeadline;
		this.password = password;
		this.profile = profile;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", address=" + address + ", userType="
				+ userType + ", profileHeadline=" + profileHeadline + ", password=" + password + ", profile=" + profile
				+ "]";
	}
}
