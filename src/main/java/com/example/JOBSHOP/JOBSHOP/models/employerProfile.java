package com.example.JOBSHOP.JOBSHOP.models;

import com.example.JOBSHOP.JOBSHOP.services.followService;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class employerProfile extends userProfile{

	@OneToOne
	@JoinColumn(name="employer_id") 
	private Employer employer;

	
	public employerProfile()
	{
		super();
	}
	public employerProfile(Employer employer,followService folloService) {
		super(employer,folloService);
		this.employer = employer;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
}
