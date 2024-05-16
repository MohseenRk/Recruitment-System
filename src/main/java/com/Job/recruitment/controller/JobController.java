package com.hexaware.recruitment.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.recruitment.dto.JobDTO;
import com.hexaware.recruitment.entity.Job;
import com.hexaware.recruitment.entity.User;
import com.hexaware.recruitment.service.IJobService;
import com.hexaware.recruitment.service.IUserService;

@RestController
@RequestMapping("/api")
public class JobController {
	
	@Autowired IJobService jobService;
	@Autowired IUserService userService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/admin/job")
	public ResponseEntity<String> createJob(@RequestBody JobDTO job){
		Job newJob = new Job();
		newJob.setCompanyName(job.getCompanyName());
		newJob.setDescription(job.getDescription());
		newJob.setPostedOn(LocalDate.now());
		newJob.setTitle(job.getTitle());
		User user = userService.getUserById(job.getPostedByUser());
		newJob.setPostedBy(user);
		Job savedJob = jobService.createJob(newJob);
		if(savedJob != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Job Created Successfully");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Job Creation Failed");
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/admin/job/{job_id}")
	public ResponseEntity<String> viewJob(@PathVariable(name="job_id") long job_id){
	    String job = jobService.viewJobById(job_id);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(job);
	}

	@GetMapping("/jobs")
	public ResponseEntity<List<Job>> viewAllJobs(){
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(jobService.viewAllJobs());
	}

	@PreAuthorize("hasAuthority('Applicant')")
	@PutMapping("/jobs/{job_id}/apply")
	public ResponseEntity<String> applyToJob(@PathVariable(name="job_id") long job_id){
	    jobService.applyToJob(job_id);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Job applied Successfully for JobId: "+job_id);
	}
}
