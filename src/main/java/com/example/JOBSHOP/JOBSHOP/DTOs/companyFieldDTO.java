package com.example.JOBSHOP.JOBSHOP.DTOs;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;


public class companyFieldDTO extends baseEntityDTO<Long>{

	private String fieldName;
	
	private companyAdministrator companyAdministrator;
	
	private List<String> requiredQualifications;
	private List<String> skills;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public companyAdministrator getCompanyAdmin() {
		return companyAdministrator;
	}
	public void setCompanyAdmin(companyAdministrator companyAdmin) {
		this.companyAdministrator = companyAdmin;
	}
	public List<String> getRequiredQualifications() {
		return requiredQualifications;
	}
	public void setRequiredQualifications(List<String> requiredQualifications) {
		this.requiredQualifications = requiredQualifications;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	
}


