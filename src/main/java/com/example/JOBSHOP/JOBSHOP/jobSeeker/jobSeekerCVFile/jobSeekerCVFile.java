package com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeekerCVFile;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.CV.CVFile;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class jobSeekerCVFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "jobSeeker_id")
	private jobSeeker jobSeeker;
	
	@OneToOne
	@JoinColumn(name="CVFile_id")
	private CVFile CVFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public CVFile getCVFile() {
		return CVFile;
	}

	public void setCVFile(CVFile cVFile) {
		CVFile = cVFile;
	}
	
	
}
