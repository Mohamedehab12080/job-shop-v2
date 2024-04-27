package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class companyProfile extends userProfile{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="companyAdministrator_id")
	private companyAdministrator companyAdministrator;
	
	@OneToMany(mappedBy = "companyProfile",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> posts=new ArrayList<Post>();
	
	@Formula("(select count(*)from post posts where posts.company_profile_id = id)")//Query between() subQuery
	private Long postCount;
	
	
	
	public companyProfile()
	{
		super();
	}
	public companyProfile(companyAdministrator companyAdmin,followService followService)
	{
		super(companyAdmin,followService);
	}
	
	
	public companyProfile(companyAdministrator companyAdmin, /*List<Post> posts,*/ Long postCount,followService followService) {
		super(companyAdmin,followService);
		this.companyAdministrator = companyAdmin;
//		this.posts = posts;
		this.postCount = postCount;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public companyAdministrator getCompanyAdministrator() {
		return companyAdministrator;
	}
	public void setCompanyAdministrator(companyAdministrator companyAdministrator) {
		this.companyAdministrator = companyAdministrator;
	}
	public void setPostCount(Long postCount) {
		this.postCount = postCount;
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

	
}
