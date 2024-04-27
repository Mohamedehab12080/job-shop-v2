package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile;

import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class jobSeekerProfile extends userProfile{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="jobSeeker_id")
	//@NotNull
	private jobSeeker jobSeeker;

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
