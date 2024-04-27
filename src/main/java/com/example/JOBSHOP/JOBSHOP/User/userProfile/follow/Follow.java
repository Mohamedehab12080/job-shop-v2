package com.example.JOBSHOP.JOBSHOP.User.userProfile.follow;
import com.example.JOBSHOP.JOBSHOP.Base.baseEntity;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Follow extends baseEntity<Long>{
	
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "followerId")
    private User follower;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "followingId")
    private User following;
    
	public User getFollower() {
		return follower;
	}
	public void setFollower(User follower) {
		this.follower = follower;
	}
	public User getFollowing() {
		return following;
	}
	public void setFollowing(User following) {
		this.following = following;
	}
	
}
