package com.example.JOBSHOP.JOBSHOP.models;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends baseEntity<Long>{

	@Column(nullable = false)
	protected String userName;
	
	// Use @Lob annotation for large objects like images
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB") 
    private byte[] picture;
    
	@Column(nullable=false)
//	@Convert(converter=PasswordConverter.class)
	private String password;
	
	@Column(nullable = false)
	protected String userType;
	
	@Column(unique = true)
	private String email;
	
	protected String Address;
	
	protected List<String> contacts;
	
	
	public User() {
		super();
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
