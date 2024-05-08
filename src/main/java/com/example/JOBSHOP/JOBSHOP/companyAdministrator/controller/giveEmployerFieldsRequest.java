package com.example.JOBSHOP.JOBSHOP.companyAdministrator.controller;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldForInsert;

public class giveEmployerFieldsRequest {
	
	private List<employerFieldForInsert> employerFields;
	
	public List<employerFieldForInsert> getEmployerFields() {
		return employerFields;
	}
	public void setEmployerFields(List<employerFieldForInsert> employerFields) {
		this.employerFields = employerFields;
	}
	
}
