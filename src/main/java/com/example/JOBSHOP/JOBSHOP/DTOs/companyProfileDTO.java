package com.example.JOBSHOP.JOBSHOP.DTOs;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;


import java.util.List;
public class companyProfileDTO extends baseEntityDTO<Long>{

	private String companyName;
	
	private String adminUserName;
	
	private List<String> contacts;
	
	private List<String>fields;
	
	private List<String>employersUserName;
	 
	private byte[] picture;
	
	
	
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
