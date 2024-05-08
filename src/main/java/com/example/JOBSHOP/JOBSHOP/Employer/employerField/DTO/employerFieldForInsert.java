package com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class employerFieldForInsert {

	
	private Long employerId;
	private Long id;
	
	public employerFieldForInsert() {}
	public employerFieldForInsert(Long employerId, Long id) {
		super();
		this.employerId = employerId;
		this.id = id;
	}
	
	public Long getEmployerId() {
		return employerId;
	}
	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
