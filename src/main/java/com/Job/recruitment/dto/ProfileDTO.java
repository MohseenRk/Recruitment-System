package com.hexaware.recruitment.dto;

public class ProfileDTO {
	private long userId;
	private String resumeFileAddress;
	private String skills;
	private String education;
	private String experience;
	private String name;
	private String email;
	private String phone;
	
	public long getApplicant() {
		return userId;
	}
	public void setApplicant(long user) {
		this.userId = user;
	}
	public String getResumeFileAddress() {
		return resumeFileAddress;
	}
	public void setResumeFileAddress(String resumeFileAddress) {
		this.resumeFileAddress = resumeFileAddress;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
