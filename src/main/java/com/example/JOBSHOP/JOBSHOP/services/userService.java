package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.repositories.userRepository;

@Service
public class userService extends BaseService{

	
	 @Autowired
	    private userRepository userRepository;
	 
	 public User insert(User user)
	 {
		 return userRepository.save(user);
	 }
	 public List<User> findAll()
	 {
		return userRepository.findAll();
	 }
	 
	 public void createUser(String name)
	 {
		 try {
			 User user=new User();
			 user.setUserName(name);
			 userRepository.save(user);
			logInfo(name+": created successfully"); 
		} catch (Exception e) {
			handleException(e); 
		}
	 }
}