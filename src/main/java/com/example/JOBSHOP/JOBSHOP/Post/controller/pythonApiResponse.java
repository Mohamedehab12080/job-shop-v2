package com.example.JOBSHOP.JOBSHOP.Post.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class pythonApiResponse {

//	Response from Flask API: [
//	                          {
//	                            "Job Title": "Family Nurse Practitioner",
//	                            "job_title_encoded": "42",
//	                            "postId": "130",
//	                            "rowType": "Train",
//	                            "skills": "Geriatric healthcare Geriatric assessment Geriatric treatment Elderly patient care Geriatric pharmacology"
//	                          }
//	                        ]
	
	@JsonProperty	("Job Title")
	private String jobTitle;
	@JsonProperty("job_title_encoded")
    private String jobTitleEncoded;
	@JsonProperty("postId")
    private String postId;
	@JsonProperty("rowType")
    private String rowType;
	 @JsonProperty("skills")
    private String skills;
	
	
    
	public String getJobTitleEncoded() {
		return jobTitleEncoded;
	}
	public void setJobTitleEncoded(String jobTitleEncoded) {
		this.jobTitleEncoded = jobTitleEncoded;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getRowType() {
		return rowType;
	}
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	
}
