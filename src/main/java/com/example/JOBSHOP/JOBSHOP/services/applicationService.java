package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.repositories.applicationRepository;

@Service
public class applicationService{

	@Autowired
	private applicationRepository applicationRepository;


    public Application getReferenceById(Long id)
	{
		return applicationRepository.getReferenceById(id);
	}
   
	public List<Application> findAll()
	{
		return applicationRepository.findAll();
	}
	
	public Application insert(Application t)
	{
		return applicationRepository.save(t);
	}
	
	public Application findById(Long id)
	{
		Optional<Application> finded=applicationRepository.findById(id);
		if(finded.isPresent())
		{
			return finded.get();
		}else 
		{
			return null;
		}
		
	}
	
//	public void updateEntityStatus(Application t)
//	{
//		applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	public Application update(Application t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return applicationRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	public List<Application> insertAll(List<Application> entity)
	{
		return applicationRepository.saveAll(entity);
	}
	
	public void deleteById(Long id)
	{
		Application t=getReferenceById(id);
		if(t!=null)
		{
			applicationRepository.deleteById(id);
		}
	}
	public List<Application> findByJobSeekerId(Long id) {
		return applicationRepository.findByJobSeekerId(id);
	}
}
