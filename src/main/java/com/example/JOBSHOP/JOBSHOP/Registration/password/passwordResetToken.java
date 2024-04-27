
package com.example.JOBSHOP.JOBSHOP.Registration.password;

import java.util.Date;

import com.example.JOBSHOP.JOBSHOP.Registration.tokens.tokenExpirationTime;
import com.example.JOBSHOP.JOBSHOP.User.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class passwordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	
	private Date expirationTime;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

	public passwordResetToken()
	{
		super();
	}
	
	public passwordResetToken(String token, User user) {
		super();
		this.token = token;
		this.expirationTime = tokenExpirationTime.getExpirationTime();
		this.user = user;
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
