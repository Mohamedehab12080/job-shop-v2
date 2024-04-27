package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.degrees.qualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class jobSeekerQualification extends baseEntity<Long>{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="jobSeeker_id")
	private jobSeeker jobSeeker;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="qualification_id")
	private qualification qualification;

	private String qualificationDegree;
	
	public String getQualificationDegree() {
		return qualificationDegree;
	}

	public void setQualificationDegree(String qualificationDegree) {
		this.qualificationDegree = qualificationDegree;
	}

	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public qualification getQualification() {
		return qualification;
	}

	public void setQualification(qualification qualification) {
		this.qualification = qualification;
	}
	
	
}
