package com.example.JOBSHOP.JOBSHOP.employerField;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.companyField.companyField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class employerField extends baseEntity<Long>{

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
    @JoinColumn(name="employer_id")
	//@NotNull
	private Employer employer;
	
	@ManyToOne
	@JoinColumn(name="companyField_id")
	//@NotNull
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
