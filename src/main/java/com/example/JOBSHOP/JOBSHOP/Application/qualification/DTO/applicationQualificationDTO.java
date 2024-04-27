package com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;

public class applicationQualificationDTO {

	private Long id;
	private Long jobSeekerQualificationId;
	private Long applicationId;
	private jobSeekerQualification jobSeekerQualification;
	private Application application;
	private String qualificationName;
	private String qualificationDegree;
	
	
	public String getQualificationDegree() {
		return qualificationDegree;
	}
	public void setQualificationDegree(String qualificationDegree) {
		this.qualificationDegree = qualificationDegree;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJobSeekerQualificationId() {
		return jobSeekerQualificationId;
	}
	public void setJobSeekerQualificationId(Long jobSeekerQualificationId) {
		this.jobSeekerQualificationId = jobSeekerQualificationId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public jobSeekerQualification getJobSeekerQualification() {
		return jobSeekerQualification;
	}
	public void setJobSeekerQualification(jobSeekerQualification jobSeekerQualification) {
		this.jobSeekerQualification = jobSeekerQualification;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	
	
	
}
