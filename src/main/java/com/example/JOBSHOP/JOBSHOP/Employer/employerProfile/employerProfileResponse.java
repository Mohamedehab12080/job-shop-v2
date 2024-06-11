package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;

public class employerProfileResponse {

	
	private employerProfile profile;
	private boolean isRquestUser;
	private employerDTO employerDto;
	private List<postDTO> posts;
	
	public employerProfileResponse() {}
	public employerProfileResponse(employerProfile profile, boolean isrequestUser, employerDTO employerDTO ,List<postDTO> posts) {
		this.employerDto=employerDTO;
		this.isRquestUser=isrequestUser;
		this.profile=profile;
		this.posts=posts;
	}
	
	
	public List<postDTO> getPosts() {
		return posts;
	}
	public void setPosts(List<postDTO> posts) {
		this.posts = posts;
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
