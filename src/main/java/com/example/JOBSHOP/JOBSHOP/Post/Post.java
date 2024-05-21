package com.example.JOBSHOP.JOBSHOP.Post;

import java.util.ArrayList;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Formula;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post extends baseEntity<Long>{

	private String Title;
	@Column(length = 1000)
	private String description;
	@Column(length = 1000)
	private String jobRequirments;
	private String location;
	private String employmentType;
	private String experience;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="companyProfile_id")
	private companyProfile companyProfile;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employer_id")
	private Employer employer;
	
	private String image;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
//	private List<postField> postFields=new ArrayList<postField>();
	
	@OneToOne
	@JoinColumn(name="postField_id")
	private postField postFields;
	
//	@Formula("(select count(*) from post_field post_field where post_field.post_id = id)")//Query between()
//    private Long fieldCount;

//	private List<String> skills;
	
	@JsonIgnore
	@OneToMany(mappedBy = "Post",cascade = CascadeType.ALL)
	private List<Application> applications=new ArrayList<Application>();
	
	@Formula("(select count(*) from application application where application.post_id = id)")//Query between()
	private Long applicationCount;
	
	
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
	
	public postField getPostFields() {
		return postFields;
	}
	public void setPostFields(postField postFields) {
		this.postFields = postFields;
	}
	//	public List<postField> getPostFields() {
//		return postFields;
//	}
//	public void setPostFields(List<postField> postFields) {
//		this.postFields = postFields;
//		
//	}
//	public Long getFieldCount() {
//		return fieldCount;
//	}
//	public void setFieldCount(Long fieldCount) {
//		this.fieldCount = fieldCount;
//	}
//	public List<String> getSkills() {
//		return skills;
//	}
//	public void setSkills(List<String> additionalSkills) {
//		this.skills = additionalSkills;
//	}
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
