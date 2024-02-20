package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JOBSHOP.JOBSHOP.models.postField;
import com.example.JOBSHOP.JOBSHOP.repositories.postFieldRepository;

@Service
public class postFieldService {

	@Autowired 
	private postFieldRepository postFieldRepository;
	
	
    public postField getReferenceById(Long id)
	{
		return postFieldRepository.getReferenceById(id);
	}
   
	public List<postField> findAll()
	{
		return postFieldRepository.findAll();
	}
	
	public postField insert(postField t)
	{
		return postFieldRepository.save(t);
	}
	
	public postField findById(Long id)
	{
		Optional<postField> finded=postFieldRepository.findById(id);
		if(finded.isPresent())
		{
			return finded.get();
		}else 
		{
			return null;
		}
		
	}
	
//	public void updateEntityStatus(postField t)
//	{
//		postFieldRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	public postField update(postField t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return postFieldRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	public List<postField> insertAll(List<postField> entity)
	{
		return postFieldRepository.saveAll(entity);
	}
	
	public void deleteById(Long id)
	{
		postField t=getReferenceById(id);
		if(t!=null)
		{
			postFieldRepository.deleteById(id);
		}
	}
}
