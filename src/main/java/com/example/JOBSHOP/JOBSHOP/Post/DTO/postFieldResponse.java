package com.example.JOBSHOP.JOBSHOP.Post.DTO;

import java.util.List;

public class postFieldResponse {

	private String fieldName;
	private List<String> Skills;
	private List<String> qualifications;
	
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
	public List<String> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	
	
}
