package com.example.JOBSHOP.JOBSHOP.Application.skill;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class applicationSkill {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="jobSeekerSkill_id")
	private jobSeekerSkill jobSeekerSkill;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="Application_id")
	private Application application;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public jobSeekerSkill getJobSeekerSkill() {
		return jobSeekerSkill;
	}

	public void setJobSeekerSkill(jobSeekerSkill jobSeekerSkill) {
		this.jobSeekerSkill = jobSeekerSkill;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application applicationn) {
		this.application = applicationn;
	}
	
	
}
