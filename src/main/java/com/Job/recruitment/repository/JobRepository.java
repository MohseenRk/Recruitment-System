package com.hexaware.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hexaware.recruitment.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	@Modifying
	@Query("UPDATE Job job SET job.totalApplications = job.totalApplications + 1 WHERE job.jobId = :job_id")
	void applyForJob(@Param("job_id") long job_id);



}
