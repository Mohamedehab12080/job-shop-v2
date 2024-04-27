package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.DTO;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
public class companyProfileDTO{

	private Long id;
	private String companyName;
	private String adminUserName;
	private Long adminId;
	private List<String> contacts;
	private List <Post> posts;
	private List<String>fields;
	private companyAdministrator companyAdmin;
	private List<String>employersUserName;
	private List<UserDTO> Followers;
	private int followersCount;
	private int followingsCount;
	private List<UserDTO> Following;
	private byte[] picture;
	
	
	

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFollowingsCount() {
		return followingsCount;
	}

	public void setFollowingsCount(int followingsCount) {
		this.followingsCount = followingsCount;
	}

	public List<UserDTO> getFollowers() {
		return Followers;
	}

	public void setFollowers(List<UserDTO> followers) {
		this.Followers = followers;
	}

	public List<UserDTO> getFollowing() {
		return Following;
	}

	public void setFollowing(List<UserDTO> following) {
		Following = following;
	}

	public companyAdministrator getCompanyAdmin() {
		return companyAdmin;
	}

	public void setCompanyAdmin(companyAdministrator companyAdmin) {
		this.companyAdmin = companyAdmin;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getContacts() {
		return contacts;
	}

	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<String> getEmployersUserName() {
		return employersUserName;
	}

	public void setEmployersUserName(List<String> employersUserName) {
		this.employersUserName = employersUserName;
	}

}
