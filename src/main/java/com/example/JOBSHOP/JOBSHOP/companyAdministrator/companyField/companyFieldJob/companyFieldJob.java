package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class companyFieldJob {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@ManyToOne // One companyField to many companyFieldSkill
	@JoinColumn(name="companyField_id")
	private companyField companyField;
	
	@ManyToOne
	@JoinColumn(name="jobModel_id")
	private jobModel jobModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public companyField getCompanyField() {
		return companyField;
	}

	public void setCompanyField(companyField companyField) {
		this.companyField = companyField;
	}

	public jobModel getJobModel() {
		return jobModel;
	}

	public void setJobModel(jobModel jobModel) {
		this.jobModel = jobModel;
	}
	
	
}
