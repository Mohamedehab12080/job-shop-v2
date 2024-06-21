package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.DTO;

public class companyFieldJobSkillDTO {
	
	private Long id;
	
	private Long companyFieldJobId;
	private Long jobId;
	private Long skillId;
	
	private String jobName;
	
	private String skillName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyFieldJobId() {
		return companyFieldJobId;
	}

	public void setCompanyFieldJobId(Long companyFieldJobId) {
		this.companyFieldJobId = companyFieldJobId;
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
	
	
	
}
