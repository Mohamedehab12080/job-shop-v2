package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;

public class companyProfileResponse {

	private companyProfile profile;
	private companyAdministratorDTO companyDto;
	private boolean isRequestUser;
	
	private List<postDTO>posts;
	
	public companyProfileResponse() {}
	public companyProfileResponse(companyProfile profile, boolean isRequestUser, companyAdministratorDTO companyDto,List<postDTO> posts) {
		super();
		this.profile = profile;
		this.companyDto = companyDto;
		this.isRequestUser = isRequestUser;
		this.posts=posts;
	}
	
	

	public List<postDTO> getPosts() {
		return posts;
	}
	public void setPosts(List<postDTO> posts) {
		this.posts = posts;
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
