package com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.DTO;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;

public class followDTO extends baseEntityDTO<Long>{

	private UserDTO follower;
	private UserDTO following;
	
	public UserDTO getFollower() {
		return follower;
	}
	public void setFollower(UserDTO follower) {
		this.follower = follower;
	}
	public UserDTO getFollowing() {
		return following;
	}
	public void setFollowing(UserDTO following) {
		this.following = following;
	}
	
	
}
