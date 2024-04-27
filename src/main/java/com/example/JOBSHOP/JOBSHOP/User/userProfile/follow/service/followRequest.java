package com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service;

import com.example.JOBSHOP.JOBSHOP.User.model.User;

public class followRequest {

	private User follower;
	private User follwing;
	
	public User getFollower() {
		return follower;
	}
	public void setFollower(User follower) {
		this.follower = follower;
	}
	public User getFollwing() {
		return follwing;
	}
	public void setFollwing(User follwing) {
		this.follwing = follwing;
	}
	
	
}
