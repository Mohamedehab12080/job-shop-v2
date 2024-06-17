package com.example.JOBSHOP.JOBSHOP.response;


public class AuthResponse {

	
	private String jwt;
	private boolean status;
	private String Message;
	
	public AuthResponse()
	{
		
	}
	public AuthResponse(String jwt, boolean status,String message) {
		super();
		this.jwt = jwt;
		this.status = status;
		this.Message=message;
	}
	
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
