package com.example.JOBSHOP.JOBSHOP.Employer.controller;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO.employerFieldForInsert;

/**
 *@author BOB
 *@function Helper class for take a request body contains employer object and list of employerFields
 */
public class employerInsertRequest {

	private employerDTO employerDto;
	
	private List<employerFieldForInsert> employerFields;
	
	public void setEmployer(employerDTO employerDto) {
		this.employerDto = employerDto;
	}
	public employerDTO getEmployerDto() {
		return employerDto;
	}
	public void setEmployerDto(employerDTO employerDto) {
		this.employerDto = employerDto;
	}
	public List<employerFieldForInsert> getEmployerFields() {
		return employerFields;
	}
	public void setEmployerFields(List<employerFieldForInsert> employerFields) {
		this.employerFields = employerFields;
	}
	
	
}
