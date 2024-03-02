package com.example.JOBSHOP.JOBSHOP.Employer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.User.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.employerField.employerField;
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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="employer")
public class Employer extends User{

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "companyAdministrator_id")
    //@NotNull
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
