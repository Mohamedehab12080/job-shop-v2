package com.example.JOBSHOP.JOBSHOP.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class companyAdministratorDTO extends UserDTO{

	private String companyName;
	
	@JsonIgnore
	private List<Employer> employers=new ArrayList<Employer>();
	
	@JsonIgnore
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
