package com.hexaware.recruitment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.recruitment.entity.Profile;
import com.hexaware.recruitment.repository.ProfileRepository;

@Service
public class ProfileService implements IProfileService {
	
	@Autowired ProfileRepository profileRepo;

}
