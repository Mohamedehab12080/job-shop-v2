package com.example.JOBSHOP.JOBSHOP.models;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobSeeker")
public class jobSeeker extends User{

	
	private String education;
	
	@Column(name="employmentState")
	private String employmentState;
	
	private String experience;
	
	private List<String> skills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL)	
	private List<Application> applications=new ArrayList<Application>();
		
 	@Formula("(select count(*) from Application app where app.job_seeker_id = id)") //Query between()
 	private Long applicationCount;  
 	
	
	
 	public Long getApplicationCount() {
 		return applicationCount;
 	}
 	public void setApplicationCount(Long applicationCount) {
 		this.applicationCount = applicationCount;
 	}
	public jobSeeker()
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
