package com.example.JOBSHOP.JOBSHOP.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="employer")
public class Employer extends User{

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "companyAdministrator_id")
    private companyAdministrator companyAdministrator;
    
 	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
 	@JsonIgnore
 	private List<Post> posts=new ArrayList<Post>();
 	
 	@Formula("(select count(*) from Post posts where posts.employer_id = id)") //Query between() subQuery
 	private Long postCount;
 	
 	@JsonIgnore
 	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
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
