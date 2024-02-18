package com.example.JOBSHOP.JOBSHOP.DTOs;

import com.example.JOBSHOP.JOBSHOP.models.Employer;


import com.example.JOBSHOP.JOBSHOP.models.companyField;

public class employerFieldDTO extends baseEntityDTO<Long>{

	
	private Employer employer;
	
	private companyField companyField;
	
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	public companyField getCompanyField() {
		return companyField;
	}
	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}
}
