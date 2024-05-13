package com.example.JOBSHOP.JOBSHOP.User.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.varification;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO{
	
	
		protected Long id;
		protected String userName;
		private String password;
		protected Role role;
		private String email;
		protected String picture;
		protected String Address;
		protected List<String> contacts;
		protected LocalDateTime createdDate;
		protected boolean isEnabled;
		protected String coverImage;
		protected varification verification;
		protected List<UserDTO> followers=new ArrayList<UserDTO>();
		
		protected List<UserDTO> followings=new ArrayList<UserDTO>();
		
		protected boolean Followed;
		protected boolean req_user;
		protected boolean is_signin_with_google;
		protected String gender;
		
		
		
		
		public String getCoverImage() {
			return coverImage;
		}
		public void setCoverImage(String coverImage) {
			this.coverImage = coverImage;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		
		public varification getVerification() {
			return verification;
		}
		public void setVerification(varification verification) {
			this.verification = verification;
		}
		public boolean isFollowed() {
			return Followed;
		}
		public void setFollowed(boolean followed) {
			Followed = followed;
		}
		public List<UserDTO> getFollowers() {
			return followers;
		}
		public void setFollowers(List<UserDTO> followers) {
			this.followers = followers;
		}
		public List<UserDTO> getFollowings() {
			return followings;
		}
		public void setFollowings(List<UserDTO> followings) {
			this.followings = followings;
		}
		public boolean isReq_user() {
			return req_user;
		}
		public void setReq_user(boolean req_user) {
			this.req_user = req_user;
		}
		public boolean isIs_signin_with_google() {
			return is_signin_with_google;
		}
		public void setIs_signin_with_google(boolean is_signin_with_google) {
			this.is_signin_with_google = is_signin_with_google;
		}
		public boolean isEnabled() {
			return isEnabled;
		}
		public void setEnabled(boolean isEnabled) {
			this.isEnabled = isEnabled;
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
		public LocalDateTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
		public String getPicture() {
			return picture;
		}
		public void setPicture(String picture) {
			this.picture = picture;
		}
		public UserDTO() {
			super();
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
		
		
	}
