package com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerFieldShowDTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTO;

public class employerFieldShowDTO {

	
	private Long id;
	private String fieldName;
	private List<String>Skills;
	private List<String>qualifications;
	private Long fieldId;
	private Long companyFieldId;
	private List<String> fieldJobs;
	private List<companyFieldJobDTO> companyFieldJobDTOs=new ArrayList<companyFieldJobDTO>();
	
	public Long getCompanyFieldId() {
		return companyFieldId;
	}
	public void setCompanyFieldId(Long companyFieldId) {
		this.companyFieldId = companyFieldId;
	}
	public List<companyFieldJobDTO> getCompanyFieldJobDTOs() {
		return companyFieldJobDTOs;
	}
	public void setCompanyFieldJobDTOs(List<companyFieldJobDTO> companyFieldJobDTOs) {
		this.companyFieldJobDTOs = companyFieldJobDTOs;
	}
	public List<String> getFieldJobs() {
		return fieldJobs;
	}
	public void setFieldJobs(List<String> fieldJobs) {
		this.fieldJobs = fieldJobs;
	}
	public Long getFieldId() {
		return fieldId;
	}
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	public List<String> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public List<String> getSkills() {
		return Skills;
	}
	public void setSkills(List<String> skills) {
		Skills = skills;
	}

	
}
