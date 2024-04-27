package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "companyField",cascade = CascadeType.ALL)
	private List<companyFieldSkill> companyFieldSkills=new ArrayList<companyFieldSkill>();	
	
	@JsonIgnore
	@OneToMany(mappedBy = "companyField",cascade = CascadeType.ALL)
	private List<companyFieldQualification> companyFieldQualifications=new ArrayList<companyFieldQualification>();	
	
	
	
	public List<companyFieldQualification> getCompanyFieldQualifications() {
		return companyFieldQualifications;
	}
	public void setCompanyFieldQualifications(List<companyFieldQualification> companyFieldQualifications) {
		this.companyFieldQualifications = companyFieldQualifications;
	}
	public String getFieldName() {
		return fieldName;
	}
	public companyAdministrator getCompanyAdministrator() {
		return companyAdministrator;
	}
	public void setCompanyAdministrator(companyAdministrator companyAdministrator) {
		this.companyAdministrator = companyAdministrator;
	}
	public List<companyFieldSkill> getCompanyFieldSkills() {
		return companyFieldSkills;
	}
	public void setCompanyFieldSkills(List<companyFieldSkill> companyFieldSkills) {
		this.companyFieldSkills = companyFieldSkills;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public companyAdministrator getCompanyAdmin() {
		return companyAdministrator;
	}

	
	
}
