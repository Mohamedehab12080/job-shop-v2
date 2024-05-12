package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.controller.skillsAndQualificationsRequest;
public class profileResponse {

	private jobSeekerProfile profile;
	
	private boolean isRequestUser;
	
	private jobSeekerDTO jobSeekerDto;
	
//	private skillsAndQualificationsRequest skillsAndQualificationsRequest;
	public profileResponse() {}
	public profileResponse(jobSeekerProfile profile, boolean isRequestUser,jobSeekerDTO jobSeekerDto/*,skillsAndQualificationsRequest skillsAndQualificationsRequest*/) {
		super();
		this.profile = profile;
		this.isRequestUser = isRequestUser;
		this.jobSeekerDto=jobSeekerDto;
//		this.skillsAndQualificationsRequest=skillsAndQualificationsRequest;
	}

//	public skillsAndQualificationsRequest getSkillsAndQualificationsRequest() {
//		return skillsAndQualificationsRequest;
//	}
//	public void setSkillsAndQualificationsRequest(skillsAndQualificationsRequest skillsAndQualificationsRequest) {
//		this.skillsAndQualificationsRequest = skillsAndQualificationsRequest;
//	}
	
	
	public jobSeekerProfile getProfile() {
		return profile;
	}

	public jobSeekerDTO getJobSeekerDto() {
		return jobSeekerDto;
	}
	public void setJobSeekerDto(jobSeekerDTO jobSeekerDto) {
		this.jobSeekerDto = jobSeekerDto;
	}
	public void setProfile(jobSeekerProfile profile) {
		this.profile = profile;
	}

	public boolean isRequestUser() {
		return isRequestUser;
	}

	public void setRequestUser(boolean isRequestUser) {
		this.isRequestUser = isRequestUser;
	}
	
	
}
