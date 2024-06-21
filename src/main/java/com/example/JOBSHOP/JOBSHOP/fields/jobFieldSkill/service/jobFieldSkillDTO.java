package com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.service;

public class jobFieldSkillDTO {

	private Long jobFieldSkillId;
	private Long jobId;
	private Long skillId;
	private String skillName;
	private String jobName;
	public Long getJobFieldSkillId() {
		return jobFieldSkillId;
	}
	public void setJobFieldSkillId(Long jobFieldSkillId) {
		this.jobFieldSkillId = jobFieldSkillId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	
}
