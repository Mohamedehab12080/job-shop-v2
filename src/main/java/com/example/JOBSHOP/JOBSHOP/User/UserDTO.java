package com.example.JOBSHOP.JOBSHOP.User;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;

public class UserDTO extends baseEntityDTO<Long>{
	
		protected String userName;
		private String password;
		protected String userType;
		private String email;
		protected byte[] picture;
		protected String Address;
		protected List<String> contacts;
		
		
		public byte[] getPicture() {
			return picture;
		}
		public void setPicture(byte[] picture) {
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
