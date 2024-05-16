package com.hexaware.recruitment.dto;

import java.time.LocalDate;

import com.hexaware.recruitment.entity.User;

import jakarta.persistence.ManyToOne;

public class JobDTO {
	private String title;
	private String description;
	private LocalDate postedOn;
	private int totalApplications;
	private String companyName;
	private long postedByUser;
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
	public long getPostedByUser() {
		return postedByUser;
	}
	public void setPostedByUser(long postedByUser) {
		this.postedByUser = postedByUser;
	}
	
}
