	package com.example.JOBSHOP.JOBSHOP.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.Follow;
import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.repositories.followRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.userRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class followService extends BaseService{

	@Autowired
	private followRepository followRepository;
	@Autowired
	private userRepository userRepository;
	
	public List<Follow> getAll()
	{
		return followRepository.findAll();
	}
	public Follow createFollow(Long followerId,Long followingId)
	{
		User follower=userRepository.findById(followerId).orElseThrow(()->new EntityNotFoundException("follower not found!"));
		User following=userRepository.findById(followingId).orElseThrow(()->new EntityNotFoundException("following not found!"));
		Follow follow=new Follow();
		follow.setFollower(follower);
		follow.setFollowing(following);
		
		return followRepository.save(follow);
	}
	
	public List<String>getFollowersUserNameById(User user)
	{
		ArrayList<String> userList=new ArrayList<String>();
		List<Follow> followList=followRepository.findByFollowing(user);
		Iterator<Follow> iterator=followList.iterator();
		while(iterator.hasNext())
		{
			Follow follow=iterator.next();
			userList.add(follow.getFollower().getUserName());
		}
		return userList;
	}
	public List<User>getFollowersById(User user)
	{
		ArrayList<User> userList=new ArrayList<User>();
		List<Follow> followList=followRepository.findByFollowing(user);
		Iterator<Follow> iterator=followList.iterator();
		while(iterator.hasNext())
		{
			Follow follow=iterator.next();
			userList.add(follow.getFollower());
		}
		return userList;
	}
	public List<User>getFollowingById(User user)
	{
		ArrayList<User> userList=new ArrayList<User>();
		List<Follow> followList=followRepository.findByFollower(user);
		Iterator<Follow> iterator=followList.iterator();
		while(iterator.hasNext())
		{
			Follow follow=iterator.next();
			userList.add(follow.getFollowing());
		}
		return userList;
	}
} 
