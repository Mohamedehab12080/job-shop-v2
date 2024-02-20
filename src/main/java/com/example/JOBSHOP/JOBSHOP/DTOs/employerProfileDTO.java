package com.example.JOBSHOP.JOBSHOP.DTOs;

import java.util.List;

/**
 **
 *@author BOB
 *@Class 
 *Data Transfer Object for employer Profile get all needed info about employer for his profile 
 **/ 
public class employerProfileDTO {

	private Long profileId;
	
	private String employerUserName;
	private String employerEmail;
	private byte[] employerPicture;
	private List <String> employerFields;
	private String companyName;
	
	
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
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
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
	public byte[] getEmployerPicture() {
		return employerPicture;
	}
	public void setEmployerPicture(byte[] employerPicture) {
		this.employerPicture = employerPicture;
	}

}
