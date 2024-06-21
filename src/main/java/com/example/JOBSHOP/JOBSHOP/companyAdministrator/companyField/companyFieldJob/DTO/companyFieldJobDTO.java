package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.DTO.companyFieldJobSkillDTO;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTO;

public class companyFieldJobDTO {

	private Long id;
	private String jobName;
	private Long companyFieldJobId;
	private Long jobId;
	private String fieldName;
	private Long companyFieldId;
	private Long fieldId;
	private companyFieldJobSkillDTO companyFieldJobSkillDTO;
	
	private List<String> skillsName=new ArrayList<String>();
	
	private List<String> qualificationsName=new ArrayList<String>();
	
	
	
	public List<String> getQualificationsName() {
		return qualificationsName;
	}
	public void setQualificationsName(List<String> qualificationsName) {
		this.qualificationsName = qualificationsName;
	}
	public List<String> getSkillsName() {
		return skillsName;
	}
	public void setSkillsName(List<String> skillsName) {
		this.skillsName = skillsName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public companyFieldJobSkillDTO getCompanyFieldJobSkillDTO() {
		return companyFieldJobSkillDTO;
	}
	public void setCompanyFieldJobSkillDTO(companyFieldJobSkillDTO companyFieldJobSkillDTO) {
		this.companyFieldJobSkillDTO = companyFieldJobSkillDTO;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
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
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getCompanyFieldId() {
		return companyFieldId;
	}
	public void setCompanyFieldId(Long companyFieldId) {
		this.companyFieldId = companyFieldId;
	}
	public Long getFieldId() {
		return fieldId;
	}
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	
}
