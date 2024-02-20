package com.example.JOBSHOP.JOBSHOP.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class companyAdministrator extends User{

	@Column(nullable = false,unique = true)
	//@NotBlank
	private String companyName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "companyAdministrator",cascade = CascadeType.ALL)
	@Column(name="companyAdministrator_employers")
	private List<Employer> employers=new ArrayList<Employer>();
	
	@OneToMany(mappedBy = "companyAdministrator",cascade = CascadeType.ALL)
	@JsonIgnore
	@Column(name="companyAdministrator_companyFields")
	private List<companyField> companyFields=new ArrayList<companyField>();
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<Employer> getEmployers() {
		return employers;
	}
	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}
	public List<companyField> getCompanyFields() {
		return companyFields;
	}
	public void setCompanyFields(List<companyField> companyFields) {
		this.companyFields = companyFields;
	}
	
}
