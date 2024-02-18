package com.example.JOBSHOP.JOBSHOP.DTOs;
import java.util.ArrayList;
import java.util.List;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.postField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
public class postDTO extends baseEntityDTO<Long>{

	private String Title;
	private String description;
	private String jobRequirments;
	private String postState;
	private String location;
	private String employmentType;
	
	
	private companyProfile companyProfile;
	
	private Employer employer;
	
	@JsonIgnore
	private List<postField> postFields=new ArrayList<postField>();
	
    private Long fieldCount;
   
	private List<String> additionalSkills;
	
	@JsonIgnore
	private List<Application> applications=new ArrayList<Application>();
	
	private Long applicationCount;
	
	
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
	public String getPostState() {
		return postState;
	}
	public void setPostState(String postState) {
		this.postState = postState;
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
	public companyProfile getCompanyProfile() {
		return companyProfile;
	}
	public void setCompanyProfile(companyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
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
