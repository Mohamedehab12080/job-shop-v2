package com.example.JOBSHOP.JOBSHOP.models;

import jakarta.persistence.Entity;
import com.example.JOBSHOP.JOBSHOP.services.followService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class jobSeekerProfile extends userProfile{

	
	@OneToOne
	@JoinColumn(name="jobSeeker_id")
	private jobSeeker jobSeeker;

	public jobSeekerProfile()
	{
		super();
		//default constructor is needed for jpa 
	}
	public jobSeekerProfile(jobSeeker jobSeeker,followService followService) {
		super(jobSeeker,followService);
		this.jobSeeker = jobSeeker;
	}

	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
	
}
