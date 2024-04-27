package com.example.JOBSHOP.JOBSHOP.User.userProfile;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;


@Component
public class userProfile{

	
	private List<User> followers;
	private List<User> followings;
	
	 
	private followService followService;
	
	@Autowired
	public userProfile(followService followService)
	{
		this.followService=followService;
	}
	public userProfile()
	{
		
	}
	
	  public userProfile(User user, followService followService) {
	        this.followService = followService;
	        this.followers = followService.getFollowersById(user);
	        this.followings = followService.getFollowingById(user);
	    }
	
//	public List<User> getFollowers(User user)
//	{
//		return followService.getFollowersById(user);
//	}
//	public List<User> getFollowings(User user)
//	{
//		
//		return followService.getFollowingById(user);
//	}
	  
	  
	public List<User> getFollowers() {
		return followers;
	}
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	public List<User> getFollowings() {
		return followings;
	}
	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}
	
	
}
