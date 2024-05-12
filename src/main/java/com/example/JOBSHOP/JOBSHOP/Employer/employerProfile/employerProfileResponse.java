package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile;

import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;

public class employerProfileResponse {

	
	private employerProfile profile;
	private boolean isRquestUser;
	private employerDTO employerDto;
	
	public employerProfileResponse() {}
	public employerProfileResponse(employerProfile profile, boolean isrequestUser, employerDTO employerDTO) {
		this.employerDto=employerDTO;
		this.isRquestUser=isrequestUser;
		this.profile=profile;
	}
	public employerProfile getProfile() {
		return profile;
	}
	public void setProfile(employerProfile profile) {
		this.profile = profile;
	}
	public boolean isRquestUser() {
		return isRquestUser;
	}
	public void setRquestUser(boolean isRquestUser) {
		this.isRquestUser = isRquestUser;
	}
	public employerDTO getEmployerDto() {
		return employerDto;
	}
	public void setEmployerDto(employerDTO employerDto) {
		this.employerDto = employerDto;
	}

}
