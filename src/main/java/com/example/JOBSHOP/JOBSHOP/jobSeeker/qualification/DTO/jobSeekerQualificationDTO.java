package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

public class jobSeekerQualificationDTO extends baseEntityDTO<Long> {

	private String qualificationName;
	private Long qualificationId;
	private Qualification qualification;
	private jobSeeker jobSeeker;
	private Long jobSeekerId;
	private String qualificationDegree;
	
	public String getQualificationDegree() {
		return qualificationDegree;
	}
	public void setQualificationDegree(String qualificationDegree) {
		this.qualificationDegree = qualificationDegree;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		if(qualificationName.contains("(good)")||qualificationName.contains("(very-good)")||qualificationName.contains("(excellent)"))
		{
			setQualificationDegree(qualificationName.substring(qualificationName.indexOf("(")+1,qualificationName.indexOf(")")));
			this.qualificationName= qualificationName.substring(0,qualificationName.indexOf("(")).strip();
		}else 
		{
			this.qualificationName=qualificationName;
		}
	}
	public Long getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public jobSeeker getJobSeeker() {
		return jobSeeker;
	}
	public void setJobSeeker(jobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	
}
