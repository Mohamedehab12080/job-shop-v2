package com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerFieldShowDTO;

import java.util.List;

public class employerFieldShowDTO {

	
	private Long id;
	private String fieldName;
	private List<String>Skills;
	private List<String>qualifications;
	private Long fieldId;
	
	
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
