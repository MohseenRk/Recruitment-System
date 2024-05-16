package com.hexaware.recruitment.service;

import java.util.List;

import com.hexaware.recruitment.entity.Profile;
import com.hexaware.recruitment.entity.User;

public interface IUserService {
	boolean signup(User user);
	String login(String email, String password);
	User getUserById(long userId);
	List<User> getApplicants();
	User getApplicantById(long user_id);
	User getApplicantByUsername(String username);
	void setProfile(Profile profile);
}
