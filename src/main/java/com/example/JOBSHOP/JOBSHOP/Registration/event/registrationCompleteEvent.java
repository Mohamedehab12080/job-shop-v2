package com.example.JOBSHOP.JOBSHOP.Registration.event;

import org.springframework.context.ApplicationEvent;

import com.example.JOBSHOP.JOBSHOP.User.model.User;

public class registrationCompleteEvent extends ApplicationEvent{


	private User user;
	private String applicationUrl;
	/**
	 * @param User user and String applicationUrl
	 * @return return them for the registration listener to make its operations 
	 * that include completing the url for (Email verification) and get the user object to save the verification Token to the database
	 */
	public registrationCompleteEvent(User user,String applicationUrl) {
		super(user);
		this.user=user;
		this.applicationUrl=applicationUrl;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getApplicationUrl() {
		return applicationUrl;
	}
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	
	
}
