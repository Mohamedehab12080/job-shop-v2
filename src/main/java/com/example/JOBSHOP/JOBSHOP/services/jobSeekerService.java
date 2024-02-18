package com.example.JOBSHOP.JOBSHOP.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.DTOImpl.DTOToEntityMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.repositories.applicationRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.jobSeekerProfileRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.jobSeekerRepository;

@Service
public class jobSeekerService extends BaseService{

	
	 @Autowired
	 private jobSeekerRepository jobSeekerRepository;
	 
	 @Autowired 
	 private jobSeekerProfileRepository jobSeekerProfileRepository;
	 @Autowired
	 private followService followSerivice;
	 
	 @Autowired
	 private applicationRepository applicationRepository;
//	 public List<User> getJobSeekerFollowers(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowersById(jobSeeker);
//	 }
//	 public List<User> getJobSeekerFollowings(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowingById(jobSeeker);
//	 }
	 
	 
	
	 public List<Application> findAllApplicationsForJobSeeker(Long id)
	 {
		return applicationRepository.findByJobSeekerId(id);
	 }
	 public Application applyForPost(applicationDTO app)
	 {
		 Application apps=DTOToEntityMapper.mapDTOToApplication(app);
		 return applicationRepository.save(apps);
	 }
	 
	 public jobSeeker getJobSeekerWithID(Long id)
	 {
		 return jobSeekerRepository.findById(id).get();
	 }
	 public jobSeeker insert(jobSeeker jobSeeker)
	 {
		 return jobSeekerRepository.save(jobSeeker);
	 }
	 public jobSeekerProfile insertProfile(jobSeekerProfile jobSeekerProfile)
	 {
		 return jobSeekerProfileRepository.save(jobSeekerProfile);
	 }
	 public List<jobSeekerProfile> findJobSeekerProfileWithjobSeeker(jobSeeker jobSeeker)
	 {
		return jobSeekerProfileRepository.findByJobSeeker(jobSeeker);
	 } 
	 public jobSeekerProfile findJobSeekerProfileWithjobSeekerID(Long jobSeekerId)
	 {
		return jobSeekerProfileRepository.findByJobSeeker_id(jobSeekerId).get();
	 } 
	 public List<jobSeekerProfile> findAllProfiles()
	 {
		return jobSeekerProfileRepository.findAll();
	 }
	 public List<jobSeeker> findAll()
	 {
		return jobSeekerRepository.findAll();
	 }
	 
	 public void jobSeeker(String name)
	 {
		 try {
			 List <String> skills=new ArrayList<String>();
			 skills.add("IT");
			 skills.add("Cs");
			 skills.add("Web");
			 jobSeeker jobSeeker=new jobSeeker();
			 jobSeeker.setUserName(name);
			 jobSeeker.setSkills(skills);
			 jobSeekerRepository.save(jobSeeker);
			logInfo(name+": created successfully"); 
		} catch (Exception e) {
			handleException(e); 
		}
	 }
}