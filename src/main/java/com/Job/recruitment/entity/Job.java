package com.hexaware.recruitment.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Job {
	@Id
	@SequenceGenerator(name = "job_sequence", initialValue = 101)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence")
	private long jobId;
	private String title;
	private String description;
	private LocalDate postedOn;
	private int totalApplications;
	private String companyName;
	@ManyToOne
	private User postedBy;
		
	public Job() {
	}
	public Job(int jobId, String title, String description, LocalDate postedOn, int totalApplications, String companyName,
			User postedBy) {
		super();
		this.jobId = jobId;
		this.title = title;
		this.description = description;
		this.postedOn = postedOn;
		this.totalApplications = totalApplications;
		this.companyName = companyName;
		this.postedBy = postedBy;
	}
	public long getJobId() {
		return jobId;
	}
	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(LocalDate postedOn) {
		this.postedOn = postedOn;
	}
	public int getTotalApplications() {
		return totalApplications;
	}
	public void setTotalApplications(int totalApplications) {
		this.totalApplications = totalApplications;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public User getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", title=" + title + ", description=" + description + ", postedOn=" + postedOn
				+ ", totalApplications=" + totalApplications + ", companyName=" + companyName + "]";
	}
	
	
	
}
