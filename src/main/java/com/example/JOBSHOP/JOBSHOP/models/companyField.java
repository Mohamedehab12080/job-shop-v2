package com.example.JOBSHOP.JOBSHOP.models;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class companyField extends baseEntity<Long>{

	//@NotBlank
	@Column(unique = true)
	private String fieldName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	@JoinColumn(name="companyAdministrator_id")
	@NotNull
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
