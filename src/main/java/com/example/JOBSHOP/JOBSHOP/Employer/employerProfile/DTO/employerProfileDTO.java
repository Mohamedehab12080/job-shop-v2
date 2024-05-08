package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.DTO;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;


/**
 **
 *@author BOB
 *@Class 
 *Data Transfer Object for employer Profile get all needed info about employer for his profile 
 **/ 
public class employerProfileDTO extends userProfile{

	private Long id;
	private Employer employer;
	private String employerUserName;
	private String employerEmail;
	private Long employerId;
	
	private String employerPicture;
	private List <String> employerFields;
	
	private String companyName;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployerId() {
		return employerId;
	}
	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<String> getEmployerFields() {
		return employerFields;
	}
	public void setEmployerFields(List<String> employerFields) {
		this.employerFields = employerFields;
	}
	public String getEmployerUserName() {
		return employerUserName;
	}
	public void setEmployerUserName(String employerUserName) {
		this.employerUserName = employerUserName;
	}
	public String getEmployerEmail() {
		return employerEmail;
	}
	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}
	public String getEmployerPicture() {
		return employerPicture;
	}
	public void setEmployerPicture(String employerPicture) {
		this.employerPicture = employerPicture;
	}

}
