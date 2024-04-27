package com.example.JOBSHOP.JOBSHOP.Registration.tokens;

import java.util.Calendar;

import java.util.Date;

import com.example.JOBSHOP.JOBSHOP.User.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="verification_token")
public class verificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	private Date expirationTime;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	public verificationToken()
	{
		super();
	}
	public verificationToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.expirationTime=tokenExpirationTime.getExpirationTime();
	}
	public verificationToken(String token) {
		super();
		this.token = token;
		this.expirationTime=tokenExpirationTime.getExpirationTime();
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	
	public Date getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
