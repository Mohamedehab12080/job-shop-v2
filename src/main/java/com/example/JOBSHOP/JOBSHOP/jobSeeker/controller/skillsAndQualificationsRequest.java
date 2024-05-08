package com.example.JOBSHOP.JOBSHOP.jobSeeker.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;

public class skillsAndQualificationsRequest {
 
	private Long jobSeekerId;
	private List<jobSeekerSkillDTO> jobSeekerSkillList=new ArrayList<jobSeekerSkillDTO>();
	private List<jobSeekerQualificationDTO>jobSeekerQualificationsList=new ArrayList<jobSeekerQualificationDTO>();
	private List<String> skills=new ArrayList<String>();
	private List<String>qualifications=new ArrayList<String>();
	
	
	public List<String> getSkills() {
		
		for(jobSeekerSkillDTO skillDto:jobSeekerSkillList)
		{
			skills.add(skillDto.getSkillName());
		}
		return skills;
	}
	
	public List<String> getQualifications() {
		for(jobSeekerQualificationDTO qualDto:jobSeekerQualificationsList)
		{
			qualifications.add(qualDto.getQualificationName());
		}
		return qualifications;
	}
	
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public List<jobSeekerSkillDTO> getJobSeekerSkillList() {
		return jobSeekerSkillList;
	}
	public void setJobSeekerSkillList(List<jobSeekerSkillDTO> jobSeekerSkillList) {
		this.jobSeekerSkillList = jobSeekerSkillList;
	}
	public List<jobSeekerQualificationDTO> getJobSeekerQualificationsList() {
		return jobSeekerQualificationsList;
	}
	public void setJobSeekerQualificationsList(List<jobSeekerQualificationDTO> jobSeekerQualificationsList) {
		this.jobSeekerQualificationsList = jobSeekerQualificationsList;
	}
	
	
	
}
