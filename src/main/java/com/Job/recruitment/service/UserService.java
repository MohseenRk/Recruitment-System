package com.hexaware.recruitment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hexaware.recruitment.entity.Profile;
import com.hexaware.recruitment.entity.User;
import com.hexaware.recruitment.repository.ProfileRepository;
import com.hexaware.recruitment.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
	@Autowired
	UserRepository repo;
	@Autowired ProfileRepository profileRepo;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public boolean signup(User user) {
		User responseUser = userRepo.save(user);
		if (responseUser != null) {
			return true;
		}
		return false;
	}

	@Override
	public String login(String email, String password) {
		logger.info("Someone is logging in...");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		logger.warn("Authentication run Successfully...");
		if (authentication.isAuthenticated()) {
			logger.info("Authenticated...");
			String token = jwtService.generateToken(email);
			return "Login Success.! Token: "+token;
		}
		return "Login Failed";
	}

	@Override
	public User getUserById(long userId) {
		return repo.findById(userId).orElse(null);
	}

	@Override
	public List<User> getApplicants() {
		return repo.findAllApplicants();
	}

	@Override
	public User getApplicantById(long user_id) {
		return repo.findApplicantById(user_id);
	}

	@Override
	public User getApplicantByUsername(String username) {
		return repo.findByEmail(username).orElse(null);
	}

	@Override
	public void setProfile(Profile profile) {
		profileRepo.save(profile);
	}

}
