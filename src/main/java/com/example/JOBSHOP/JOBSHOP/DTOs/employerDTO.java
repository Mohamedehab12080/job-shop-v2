package com.example.JOBSHOP.JOBSHOP.DTOs;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;

public class employerDTO extends UserDTO{
	
	
		@JsonBackReference
		//@NotNull
	    private companyAdministrator companyAdministrator;
	    
	    @JsonIgnore
	 	private List<Post> posts=new ArrayList<Post>();
	 	
	 	private Long postCount;
	 	
	 	@JsonIgnore
	 	private List<employerField> employerFields= new ArrayList<employerField>();
	    
	 	public companyAdministrator getCompanyAdmin() {
	 		return companyAdministrator;
	 	}
	 	public void setCompanyAdmin(companyAdministrator companyAdministrator) {
	 		this.companyAdministrator = companyAdministrator;
	 	}
	 	public List<Post> getPosts() {
	 		return posts;
	 	}
	 	public void setPosts(List<Post> posts) {
	 		this.posts = posts;
	 	}
	 	public Long getPostCount() {
	 		return postCount;
	 	}
	 	public void setPostCount(Long postCount) {
	 		this.postCount = postCount;
	 	}
	 	public List<employerField> getEmployerFields() {
	 		return employerFields;
	 	}
	 	public void setEmployerFields(List<employerField> employerFields) {
	 		this.employerFields = employerFields;
	 	}
		
	}
