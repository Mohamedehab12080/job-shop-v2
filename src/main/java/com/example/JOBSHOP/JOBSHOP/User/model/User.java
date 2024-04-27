package com.example.JOBSHOP.JOBSHOP.User.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@Column(nullable = false)
	protected String userName;
	
	protected boolean isEnabled=false;
	// Use @Lob annotation for large objects like images
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB") 
    private byte[] picture;
    
    protected String image;
    
	@Column(nullable=false)
//	@Convert(converter=PasswordConverter.class)
	protected String password;

	protected boolean req_user;
	protected boolean isLogin_with_google;
	
	protected Role role;
	
	@Column(unique = true)
	protected String email;
	
	protected String Address;
	
	protected List<String> contacts =new ArrayList<String>();
	
	protected LocalDateTime createdDate;
	
	@JsonIgnore
	@ManyToMany
	protected List<User> followers=new ArrayList<User>();
	
	@JsonIgnore
	@ManyToMany
	protected List<User> followings=new ArrayList<User>();
	
	
	@Embedded
	protected varification verification;

	
	
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public varification getVerification() {
		return verification;
	}


	public void setVerification(varification verification) {
		this.verification = verification;
	}


	public List<User> getFollowers() {
		return followers;
	}


	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}


	public List<User> getFollowings() {
		return followings;
	}


	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}



	public boolean isReq_user() {
		return req_user;
	}


	public void setReq_user(boolean req_user) {
		this.req_user = req_user;
	}


	public boolean isLogin_with_google() {
		return isLogin_with_google;
	}


	public void setLogin_with_google(boolean isLogin_with_google) {
		this.isLogin_with_google = isLogin_with_google;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public User() {
		super();
	}
	
	
	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getUserType() {
		return role;
	}
	public void setUserType(Role role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public List<String> getContacts() {
		return contacts;
	}
	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
