package com.hexaware.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.recruitment.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
}
