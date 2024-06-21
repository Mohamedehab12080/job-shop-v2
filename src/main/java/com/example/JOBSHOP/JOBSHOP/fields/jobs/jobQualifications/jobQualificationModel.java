package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class jobQualificationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="qualification_id")
	private Qualification qualification;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="jobModel_id")
	private jobModel jobModel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public jobModel getJobModel() {
		return jobModel;
	}

	public void setJobModel(jobModel jobModel) {
		this.jobModel = jobModel;
	}
	
	
}
