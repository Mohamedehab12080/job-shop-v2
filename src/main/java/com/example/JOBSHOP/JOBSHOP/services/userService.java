package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.repositories.userRepository;

@Service
public class userService{

	
	 @Autowired
	 private userRepository userRepository;
	 

	    public User getReferenceById(Long id)
		{
			return userRepository.getReferenceById(id);
		}
	   
		public List<User> findAll()
		{
			return userRepository.findAll();
		}
		
		public User insert(User t)
		{
			return userRepository.save(t);
		}
		
		public User findById(Long id)
		{
			Optional<User> finded=userRepository.findById(id);
			if(finded.isPresent())
			{
				return finded.get();
			}else 
			{
				return null;
			}
			
		}
		
//		public void updateEntityStatus(User t)
//		{
//			userRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		
		public User update(User t)
		{
			if(getReferenceById(t.getId())!=null)
			{
//				logInfo("Employer Updated Successfully");
				return userRepository.save(t);
			}else 
			{
//				logError("EmployerNotFound");
				return null;
				
			}
		}
		public List<User> insertAll(List<User> entity)
		{
			return userRepository.saveAll(entity);
		}
		
		public void deleteById(Long id)
		{
			User t=getReferenceById(id);
			if(t!=null)
			{
				userRepository.deleteById(id);
			}
		}
}