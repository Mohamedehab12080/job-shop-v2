package com.example.JOBSHOP.JOBSHOP.Employer;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.User.UserDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.employerField.employerField;
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
