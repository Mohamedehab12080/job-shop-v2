package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;

public class jobSeekerProfileDTO extends userProfile{

	private Long id;
	private jobSeeker jobSeeker;
	private String userName;
	private String email;
	private String Address;
	private String education;
	private String Expreience;
	private String description;
	private Long jobSeekerId;
	private List<jobSeekerSkillDTO> jobSeekerSkills=new ArrayList<jobSeekerSkillDTO>();
	private List<jobSeekerQualificationDTO> jobSeekerQualifications=new ArrayList<jobSeekerQualificationDTO>();
	private List<applicationDTO>applications=new ArrayList<applicationDTO>();
	private List<User> userFollowings=new ArrayList<User>();
	private List<User> userFollowers=new ArrayList<User>();
	private int userFollowersCount;
	private int userFollowingsCount;
	
	
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExpreience() {
		return Expreience;
	}
	public void setExpreience(String expreience) {
		Expreience = expreience;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public List<jobSeekerSkillDTO> getJobSeekerSkills() {
		return jobSeekerSkills;
	}
	public void setJobSeekerSkills(List<jobSeekerSkillDTO> jobSeekerSkills) {
		this.jobSeekerSkills = jobSeekerSkills;
	}
	public List<jobSeekerQualificationDTO> getJobSeekerQualifications() {
		return jobSeekerQualifications;
	}
	public void setJobSeekerQualifications(List<jobSeekerQualificationDTO> jobSeekerQualifications) {
		this.jobSeekerQualifications = jobSeekerQualifications;
	}
	
	public List<applicationDTO> getApplications() {
		return applications;
	}
	public void setApplications(List<applicationDTO> applications) {
		this.applications = applications;
	}
	public List<User> getUserFollowings() {
		return userFollowings;
	}
	public void setUserFollowings(List<User> userFollowings) {
		this.userFollowings = userFollowings;
	}
	public List<User> getUserFollowers() {
		return userFollowers;
	}
	public void setUserFollowers(List<User> userFollowers) {
		this.userFollowers = userFollowers;
	}
	public int getUserFollowersCount() {
		return userFollowersCount;
	}
	public void setUserFollowersCount(int userFollowersCount) {
		this.userFollowersCount = userFollowersCount;
	}
	public int getUserFollowingsCount() {
		return userFollowingsCount;
	}
	public void setUserFollowingsCount(int userFollowingsCount) {
		this.userFollowingsCount = userFollowingsCount;
	}

	
}
