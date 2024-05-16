package com.hexaware.recruitment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Profile {
	@Id
	@SequenceGenerator(name = "profile_sequence", initialValue = 1001)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence")
	private long profileId;
	@OneToOne
	private User applicant;
	private String resumeFileAddress;
	private String skills;
	private String education;
	private String experience;
	private String name;
	private String email;
	private String phone;
	
	public Profile() {
		
	}
	public Profile(long profileId, User applicant, String resumeFileAddress, String skills, String education,
			String experience, String name, String email, String phone) {
		super();
		this.profileId = profileId;
		this.applicant = applicant;
		this.resumeFileAddress = resumeFileAddress;
		this.skills = skills;
		this.education = education;
		this.experience = experience;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
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
	
	@Override
	public String toString() {
		return "Profile [resumeFileAddress=" + resumeFileAddress + ", skills=" + skills + ", education=" + education
				+ ", experience=" + experience + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
}
