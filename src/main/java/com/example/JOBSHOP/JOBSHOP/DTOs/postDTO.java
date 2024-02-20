package com.example.JOBSHOP.JOBSHOP.DTOs;
import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.postField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
public class postDTO extends baseEntityDTO<Long>{

	private String Title;
	private String description;
	private String jobRequirments;
	private String location;
	private String employmentType;
	private byte[] employerpicture;
	private String employerUserName;
//	private companyProfile companyProfile;
//	
	private String companyName;
	private Long profileId;
	private String adminUserName;
	

	//	private Employer employer;
//	
	@JsonIgnore
	private List<postField> postFields=new ArrayList<postField>();
	
    private Long fieldCount;
   
	private List<String> additionalSkills;
	
	@JsonIgnore
	private List<Application> applications=new ArrayList<Application>();
	
	private Long applicationCount;
	

	
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public byte[] getEmployerpicture() {
		return employerpicture;
	}
	public void setEmployerpicture(byte[] employerpicture) {
		this.employerpicture = employerpicture;
	}
	public String getEmployerUserName() {
		return employerUserName;
	}
	public void setEmployerUserName(String employerUserName) {
		this.employerUserName = employerUserName;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJobRequirments() {
		return jobRequirments;
	}
	public void setJobRequirments(String jobRequirments) {
		this.jobRequirments = jobRequirments;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
//	public companyProfile getCompanyProfile() {
//		return companyProfile;
//	}
//	public void setCompanyProfile(companyProfile companyProfile) {
//		this.companyProfile = companyProfile;
//	}
//	public Employer getEmployer() {
//		return employer;
//	}
//	public void setEmployer(Employer employer) {
//		this.employer = employer;
//	}
	public List<postField> getPostFields() {
		return postFields;
	}
	public void setPostFields(List<postField> postFields) {
		this.postFields = postFields;
	}
	public Long getFieldCount() {
		return fieldCount;
	}
	public void setFieldCount(Long fieldCount) {
		this.fieldCount = fieldCount;
	}
	public List<String> getAdditionalSkills() {
		return additionalSkills;
	}
	public void setAdditionalSkills(List<String> additionalSkills) {
		this.additionalSkills = additionalSkills;
	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public Long getApplicationCount() {
		return applicationCount;
	}
	public void setApplicationCount(Long applicationCount) {
		this.applicationCount = applicationCount;
	}
	
	public void insertIntoDataSet()
	{
		
	}
}
