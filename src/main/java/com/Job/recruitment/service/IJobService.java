package com.hexaware.recruitment.service;

import java.util.List;

import com.hexaware.recruitment.entity.Job;

public interface IJobService {

	List<Job> viewAllJobs();

	Job createJob(Job newJob);

	String viewJobById(long job_id);

	void applyToJob(long job_id);

}
