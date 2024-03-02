package com.example.JOBSHOP.JOBSHOP.companyField;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;


public class companyFieldDTO extends baseEntityDTO<Long>{

	private String fieldName;
	
	private Long companyAdministratorId;
	
	private String companyName;
	
	private List<String> requiredQualifications;
	private List<String> skills;
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getCompanyAdministratorId() {
		return companyAdministratorId;
	}
	public void setCompanyAdministratorId(Long companyAdminId) {
		this.companyAdministratorId = companyAdminId;
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


