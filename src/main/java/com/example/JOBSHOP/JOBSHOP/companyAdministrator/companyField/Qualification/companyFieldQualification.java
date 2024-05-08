package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;

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
	private Qualification qualification;

	public companyField getCompanyField() {
		return companyField;
	}

	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
	
	
}
