package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.DTO;

public class jobQualificationDTO {

	private Long id;
	
	private String qualificationName;
	private String JobName;
	private Long qualificationId;
	private Long jobId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public String getJobName() {
		return JobName;
	}
	public void setJobName(String jobName) {
		JobName = jobName;
	}
	
	public Long getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	
}
