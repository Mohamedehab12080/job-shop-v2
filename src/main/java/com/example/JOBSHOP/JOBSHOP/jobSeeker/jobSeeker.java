package com.example.JOBSHOP.JOBSHOP.jobSeeker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobSeeker")
@JsonIgnoreProperties(ignoreUnknown = true)
public class jobSeeker extends User{

	
	private String education;
	
	@Column(name="employmentState")
	private String employmentState;
	
	private String experience;
	
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL)	
	private List<Application> applications=new ArrayList<Application>();
		
 	@Formula("(select count(*) from Application app where app.job_seeker_id = id)") //Query between()
 	private Long applicationCount;  
 	
	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL)
	private List<jobSeekerSkill> jobSeekerSkills=new ArrayList<jobSeekerSkill>();	
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobSeeker",cascade = CascadeType.ALL)
	private List<jobSeekerQualification> jobSeekerQualifications=new ArrayList<jobSeekerQualification>();
	
	private Date birthDate;
	
 	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public List<jobSeekerQualification> getJobSeekerQualifications() {
		return jobSeekerQualifications;
	}
	public void setJobSeekerQualifications(List<jobSeekerQualification> jobSeekerQualifications) {
		this.jobSeekerQualifications = jobSeekerQualifications;
	}
	public List<jobSeekerSkill> getJobSeekerSkills() {
		return jobSeekerSkills;
	}
	public void setJobSeekerSkills(List<jobSeekerSkill> jobSeekerSkills) {
		this.jobSeekerSkills = jobSeekerSkills;
	}
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
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	
}
