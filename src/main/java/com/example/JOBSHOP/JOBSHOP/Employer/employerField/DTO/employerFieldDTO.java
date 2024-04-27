package com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;

public class employerFieldDTO extends baseEntityDTO<Long>{

	//@NotNull
	private Long employerId;
	private String employerUserName;
//	private String employerEmail;
	private String fieldName;
	private Long companyFieldId;
	private List<String> Skills;
	private Employer employer;
	
	
	public Long getCompanyFieldId() {
		return companyFieldId;
	}
	public void setCompanyFieldId(Long companyFieldId) {
		this.companyFieldId = companyFieldId;
	}
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	//@NotNull
	@JsonBackReference
	private companyField companyField;
	

	public List<String> getSkills() {
		return Skills;
	}
	public void setSkills(List<String> skills) {
		Skills = skills;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getEmployerId() {
		return employerId;
	}
	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}
	public String getEmployerUserName() {
		return employerUserName;
	}
	public void setEmployerUserName(String employerUserName) {
		this.employerUserName = employerUserName;
	}
	public companyField getCompanyField() {
		return companyField;
	}
	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}
}
