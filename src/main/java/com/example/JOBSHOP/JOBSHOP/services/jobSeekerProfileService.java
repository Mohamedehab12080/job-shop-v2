package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.repositories.jobSeekerProfileRepository;

@Service
public class jobSeekerProfileService {

	@Autowired
	private jobSeekerProfileRepository jobSeekerProfileRepository;
	

    public jobSeekerProfile getReferenceById(Long id)
	{
		return jobSeekerProfileRepository.getReferenceById(id);
	}
   
	public List<jobSeekerProfile> findAll()
	{
		return jobSeekerProfileRepository.findAll();
	}
	
	public jobSeekerProfile insert(jobSeekerProfile t)
	{
		return jobSeekerProfileRepository.save(t);
	}
	
	public jobSeekerProfile findById(Long id)
	{
		Optional<jobSeekerProfile> finded=jobSeekerProfileRepository.findById(id);
		if(finded.isPresent())
		{
			return finded.get();
		}else 
		{
			return null;
		}
		
	}
	
//	public void updateEntityStatus(jobSeekerProfile t)
//	{
//		jobSeekerProfileRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	public jobSeekerProfile update(jobSeekerProfile t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return jobSeekerProfileRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	public List<jobSeekerProfile> insertAll(List<jobSeekerProfile> entity)
	{
		return jobSeekerProfileRepository.saveAll(entity);
	}
	
	public void deleteById(Long id)
	{
		jobSeekerProfile t=getReferenceById(id);
		if(t!=null)
		{
			jobSeekerProfileRepository.deleteById(id);
		}
	}
	
	
	public Optional<jobSeekerProfile> findByJobSeekerId(Long id) {
		return jobSeekerProfileRepository.findByJobSeekerId(id);
	}
	public Optional<jobSeekerProfile> findByJobSeeker_id(Long jobSeekerId) {
		return jobSeekerProfileRepository.findByJobSeeker_id(jobSeekerId);
	}

}
