package com.example.JOBSHOP.JOBSHOP.Employer.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null properties in serialization
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties during deserialization
public class employerDTO extends UserDTO{
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
		//@NotNull
	    private companyAdministrator companyAdministrator;
		
		@JsonProperty("companyAdministratorId")
		private Long companyAdministratorId;
		private String companyName;
	    
	    @JsonIgnore
	 	private List<Post> posts=new ArrayList<Post>();
	 	
	 	private Long postCount;
	 	
	 	@JsonIgnore
	 	private List<employerField> employerFields= new ArrayList<employerField>();
	    
	 	private List <String> fieldsNames=new ArrayList<String>();
	 	
	 	
	 	
	 	public Long getCompanyAdministratorId() {
			return companyAdministratorId;
		}
		public void setCompanyAdministratorId(Long companyAdministratorId) {
			this.companyAdministratorId = companyAdministratorId;
		}
		public List<String> getFieldsNames() {
			return fieldsNames;
		}
		public void setFieldsNames(List<String> fieldsNames) {
			this.fieldsNames = fieldsNames;
		}
		public companyAdministrator getCompanyAdministrator() {
			return companyAdministrator;
		}
		public void setCompanyAdministrator(companyAdministrator companyAdministrator) {
			this.companyAdministrator = companyAdministrator;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
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
