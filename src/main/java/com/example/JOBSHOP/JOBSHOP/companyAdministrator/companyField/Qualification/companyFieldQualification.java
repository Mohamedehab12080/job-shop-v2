package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.degrees.qualification;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class companyFieldQualification extends baseEntity<Long>{

	@ManyToOne
	@JoinColumn(name="companyField_id")
	private companyField companyField;
	
	@ManyToOne
	@JoinColumn(name="qualification_id")
	private qualification qualification;

	public companyField getCompanyField() {
		return companyField;
	}

	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}

	public qualification getQualification() {
		return qualification;
	}

	public void setQualification(qualification qualification) {
		this.qualification = qualification;
	}
	
	
	
}
