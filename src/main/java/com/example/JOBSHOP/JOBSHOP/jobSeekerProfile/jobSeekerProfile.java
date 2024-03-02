package com.example.JOBSHOP.JOBSHOP.jobSeekerProfile;

import com.example.JOBSHOP.JOBSHOP.Follow.followService;
import com.example.JOBSHOP.JOBSHOP.User.userProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class jobSeekerProfile extends userProfile{

	
	@OneToOne
	@JoinColumn(name="jobSeeker_id")
	//@NotNull
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
