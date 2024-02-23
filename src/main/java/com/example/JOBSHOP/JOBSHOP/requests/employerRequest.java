package com.example.JOBSHOP.JOBSHOP.requests;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.employerField;

/**
 *@author BOB
 *@function Helper class for take a request body contains employer object and list of employerFields
 */
public class employerRequest {

	private Employer employer;
	
	private List<employerField> employerFields;
	
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	public List<employerField> getEmployerFields() {
		return employerFields;
	}
	public void setEmployerFields(List<employerField> employerFields) {
		this.employerFields = employerFields;
	}
	
}
