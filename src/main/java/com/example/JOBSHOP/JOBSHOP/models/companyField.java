package com.example.JOBSHOP.JOBSHOP.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class companyField extends baseEntity<Long>{

	private String fieldName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="companyAdministrator_id")
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
