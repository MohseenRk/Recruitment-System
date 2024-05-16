package com.hexaware.recruitment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.recruitment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.email=?1")
	Optional<User> findByEmail(String username);

	@Query("select u from User u where u.userType='Applicant'")
	List<User> findAllApplicants();

	@Query("select u from User u where u.userType='Applicant' and u.userId = :user_id")
	User findApplicantById(@Param("user_id") long user_id);

}
