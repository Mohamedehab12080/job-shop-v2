package com.example.JOBSHOP.JOBSHOP.User.service;

import com.example.JOBSHOP.JOBSHOP.User.model.User;

public class userUtils {

	public static final boolean isReqUser(User reqUser,User user2)
	{
		return reqUser.getId().equals(user2.getId());
	}
	
	public static final boolean isFollowedByReqUser(User reqUser,User user2)
	{
		return reqUser.getFollowings().contains(user2);
	}
}
