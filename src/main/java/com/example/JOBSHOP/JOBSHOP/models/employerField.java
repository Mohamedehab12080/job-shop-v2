package com.example.JOBSHOP.JOBSHOP.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class employerField extends baseEntity<Long>{

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
    @JoinColumn(name="employer_id")
	private Employer employer;
	
	@OneToOne
	@JoinColumn(name="companyField_id")
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
