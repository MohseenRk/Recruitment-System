package com.hexaware.recruitment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hexaware.recruitment.dto.LoginDTO;
import com.hexaware.recruitment.dto.ResumeInfo;
import com.hexaware.recruitment.dto.UserDTO;
import com.hexaware.recruitment.entity.Profile;
import com.hexaware.recruitment.entity.User;
import com.hexaware.recruitment.service.IUserService;
import com.hexaware.recruitment.service.ResumeParserService;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	IUserService userService;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	ResumeParserService resumeParserService;
//	@Autowired	Authentication authentication;

	Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDTO user) {
		User newUser = new User();
		newUser.setAddress(user.getAddress());
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setProfileHeadline(user.getProfileHeadline());
		newUser.setUserType(user.getUserType());
		boolean response = userService.signup(newUser);
		if (response) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("SignUp Success");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup Failed");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO login) {
		log.info("Request received to login: [Username:" + login.getEmail() + ", Password: " + login.getPassword()
				+ " ]");
		String token = userService.login(login.getEmail(), login.getPassword());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/admin/applicants")
	public ResponseEntity<List<User>> getApplicants() {
		List<User> applicants = userService.getApplicants();
		log.info("Getting all applicants");
		return ResponseEntity.status(HttpStatus.OK).body(applicants);
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/admin/applicants/{applicant_id}")
	public ResponseEntity<User> getApplicant(@PathVariable(name = "applicant_id") long applicant_id) {
		log.info("Getting Applicant by Id: "+applicant_id);
		User applicant = userService.getApplicantById(applicant_id);
		return ResponseEntity.status(HttpStatus.OK).body(applicant);
	}

	@PreAuthorize("hasAuthority('Applicant')")
	@PostMapping("/uploadResume")
	public ResponseEntity<ResumeInfo> uploadResume(@RequestParam("file") MultipartFile file,Authentication authentication)
			throws java.io.IOException {
		if (authentication == null || !authentication.isAuthenticated()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		log.info("Getting Authenticated User from the database");
		User user = userService.getApplicantByUsername(username);
		Profile profile = new Profile();
		try {
			ResumeInfo resumeInfo = resumeParserService.parseResume(file);
			profile.setApplicant(user);
			profile.setEducation(resumeInfo.getEducation());
			profile.setEmail(resumeInfo.getEmail());
			profile.setExperience(resumeInfo.getExperience());
			profile.setName(resumeInfo.getName());
			profile.setPhone(resumeInfo.getPhone());
			profile.setSkills(resumeInfo.getSkills());
			userService.setProfile(profile);
			return ResponseEntity.ok().body(resumeInfo);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
