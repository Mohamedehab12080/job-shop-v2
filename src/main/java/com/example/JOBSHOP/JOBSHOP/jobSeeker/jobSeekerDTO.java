package com.example.JOBSHOP.JOBSHOP.jobSeeker;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.User.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class jobSeekerDTO extends UserDTO{
private String education;
	
	
	private String employmentState;
	
	private String experience;
	
	private List<String> skills;
	
	@JsonIgnore
	private List<Application> applications=new ArrayList<Application>();
	
 	private Long applicationCount;  
 	
	
	
 	public Long getApplicationCount() {
 		return applicationCount;
 	}
 	public void setApplicationCount(Long applicationCount) {
 		this.applicationCount = applicationCount;
 	}
	public jobSeekerDTO()
	{
		super();
	}
//	public jobSeeker(String education, String employmentState, String experience, List<String> skills,
//			List<Application> applications) {
//		super();
//		this.education = education;
//		this.employmentState = employmentState;
//		this.experience = experience;
//		this.skills = skills;
//		this.applications = applications;
//	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmploymentState() {
		return employmentState;
	}
	public void setEmploymentState(String employmentState) {
		this.employmentState = employmentState;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	
}
