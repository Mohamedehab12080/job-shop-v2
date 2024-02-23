package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.DTOImpl.DTOToEntityMapper;
import com.example.JOBSHOP.JOBSHOP.DTOs.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.repositories.jobSeekerRepository;

@Service
public class jobSeekerService{

	
	 @Autowired
	 private jobSeekerRepository jobSeekerRepository;
	 
	 @Autowired 
	 private jobSeekerProfileService jobSeekerProfileService;
	 
	 @Autowired
	 private followService followService;
	 
	 @Autowired
	 private applicationService applicationService;
	 
	 
//	 public List<User> getJobSeekerFollowers(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowersById(jobSeeker);
//	 }
//	 public List<User> getJobSeekerFollowings(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowingById(jobSeeker);
//	 }
	 
	 

	    public jobSeeker getReferenceById(Long id)
		{
			return jobSeekerRepository.getReferenceById(id);
		}
	   
		public List<jobSeeker> findAll()
		{
			return jobSeekerRepository.findAll();
		}
		
		public jobSeeker findById(Long id)
		{
			Optional<jobSeeker> finded=jobSeekerRepository.findById(id);
			if(finded.isPresent())
			{
				return finded.get();
			}else 
			{
				return null;
			}
			
		}
		
//		public void updateEntityStatus(Application t)
//		{
//			jobSeekerRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		
		public jobSeeker update(jobSeeker t)
		{
			if(getReferenceById(t.getId())!=null)
			{
//				logInfo("Employer Updated Successfully");
				return jobSeekerRepository.save(t);
			}else 
			{
//				logError("EmployerNotFound");
				return null;
				
			}
		}
		public List<jobSeeker> insertAll(List<jobSeeker> entity)
		{
			return jobSeekerRepository.saveAll(entity);
		}
		
		public void deleteById(Long id)
		{
			jobSeeker t=getReferenceById(id);
			if(t!=null)
			{
				jobSeekerRepository.deleteById(id);
			}
		}
	 /**
	 * 
	 * @author BOB
	 * @Function Insert jobSeeker With his Profile.
	 */
	 public jobSeeker insert(jobSeeker jobSeeker)
	 {
		 jobSeekerProfile jobSeekerProfile=new jobSeekerProfile();
		 jobSeekerProfile.setJobSeeker(jobSeeker); 
		 jobSeeker jobSeekersaved=jobSeekerRepository.save(jobSeeker);
		 jobSeekerProfileService.insert(jobSeekerProfile);
		 return jobSeekersaved;
	 }
	
	 /**
	 * 
	 * @author BOB
	 * @Function find all jobSeeker's submitted applications by jobSeeker_id Order by LIFO.
	 */
	 public List<Application> findAllApplicationsForJobSeeker(Long id)
	 {
		return applicationService.findByJobSeekerIdOrderByCreatedDate(id);
	 }
	 
	 /**
	 * 
	 * @author BOB
	 * @Function JobSeeker apply for Specific Opened posted job
	 */
	 public Application applyForPost(applicationDTO app)
	 {
		 Application apps=DTOToEntityMapper.mapDTOToApplication(app);
		 return applicationService.insert(apps);
	 }
	 
	 public List<String> findSkillsById(Long id)
	 {
		 return jobSeekerRepository.findSkillsById(id);
	 }
	 /**
	 * 
	 * @author BOB
	 * @Function jobSeeker update his picture
	 */
	 public jobSeeker insertPicture(Long id,byte[] picture)
	 {
		 try {
			Optional<jobSeeker> jobSeekerUpdate=jobSeekerRepository.findById(id);
			 if(jobSeekerUpdate.isPresent())
			 {
				 System.out.println("Job Seeker :" +jobSeekerUpdate.get().getEmail());
				 jobSeeker jobSeekerForUpdate=jobSeekerUpdate.get();
				 jobSeekerForUpdate.setPicture(picture);
				 return jobSeekerRepository.save(jobSeekerForUpdate);
			 }else  
			 {
				 return null;
			 }
		} catch (Exception e) {
			return null;
		}
	 }
	 
//	 public jobSeekerProfile insertProfile(jobSeekerProfile jobSeekerProfile)
//	 {
//		 return jobSeekerProfileRepository.save(jobSeekerProfile);
//	 }
	 
	 /**
	 * 
	 * @author BOB
	 * @Function find jobSeeker profile by jobSeeker_Id 
	 */
	 public jobSeekerProfile findByJobSeekerId(Long id)
	 {
		return jobSeekerProfileService.findByJobSeekerId(id).get();
	 } 
	 
	 public jobSeekerProfile findJobSeekerProfileWithjobSeekerID(Long jobSeekerId)
	 {
		return jobSeekerProfileService.findByJobSeeker_id(jobSeekerId).get();
	 } 
	  
	 
}