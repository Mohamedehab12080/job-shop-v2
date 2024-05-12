package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;

public class companyProfileResponse {

	private companyProfile profile;
	private companyAdministratorDTO companyDto;
	private boolean isRequestUser;
	
	public companyProfileResponse() {}
	public companyProfileResponse(companyProfile profile, boolean isRequestUser, companyAdministratorDTO companyDto) {
		super();
		this.profile = profile;
		this.companyDto = companyDto;
		this.isRequestUser = isRequestUser;
	}
	
	

	public companyProfile getProfile() {
		return profile;
	}
	public void setProfile(companyProfile profile) {
		this.profile = profile;
	}
	public companyAdministratorDTO getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(companyAdministratorDTO companyDto) {
		this.companyDto = companyDto;
	}
	public boolean isRequestUser() {
		return isRequestUser;
	}
	public void setRequestUser(boolean isRequestUser) {
		this.isRequestUser = isRequestUser;
	}
	
	
}
