package com.example.JOBSHOP.JOBSHOP.jobSeeker.requests;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;

public class saveSkillsRequest {
   
	// List of skillDto that contains jobSeeker Id and skill name and skill degree
	private List<String> skills=new ArrayList<String>();
	
	private List<String> qualifications=new ArrayList<String>();

	private Long userId;
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long jobSeekerId) {
		this.userId = jobSeekerId;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<String> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	

}
