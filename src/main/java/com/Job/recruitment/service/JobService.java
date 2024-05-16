package com.hexaware.recruitment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.recruitment.entity.Job;
import com.hexaware.recruitment.repository.JobRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService implements IJobService {
	
	@Autowired JobRepository jobRepo;

	@Override
	public List<Job> viewAllJobs() {
		List<Job> jobs = jobRepo.findAll();
		return jobs;
	}

	@Override
	public Job createJob(Job newJob) {
		return jobRepo.save(newJob);
	}

	@Override
	public String viewJobById(long job_id) {
		Job job =jobRepo.findById(job_id).orElse(null);
		if(job!=null) {
			return job.toString();
		}
		return "No job match found";
	}

	@Override
	public void applyToJob(long job_id) {
		jobRepo.applyForJob(job_id);
	}
	
}
