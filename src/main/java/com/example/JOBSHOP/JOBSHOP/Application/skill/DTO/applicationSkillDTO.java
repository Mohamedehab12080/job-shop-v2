package com.example.JOBSHOP.JOBSHOP.Application.skill.DTO;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;

public class applicationSkillDTO {

	private Long id;
	private String skillName;
	private String skillDegree;
	private jobSeekerSkill jobSeekerSkill;
	private Application application;
	private Long jobSeekerSkillId;
	private Long applicationId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getSkillDegree() {
		return skillDegree;
	}
	public void setSkillDegree(String skillDegree) {
		this.skillDegree = skillDegree;
	}
	public jobSeekerSkill getJobSeekerSkill() {
		return jobSeekerSkill;
	}
	public void setJobSeekerSkill(jobSeekerSkill jobSeekerSkill) {
		this.jobSeekerSkill = jobSeekerSkill;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Long getJobSeekerSkillId() {
		return jobSeekerSkillId;
	}
	public void setJobSeekerSkillId(Long jobSeekerSkillId) {
		this.jobSeekerSkillId = jobSeekerSkillId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
}
