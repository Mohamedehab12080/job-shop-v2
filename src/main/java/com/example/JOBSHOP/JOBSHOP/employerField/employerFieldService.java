package com.example.JOBSHOP.JOBSHOP.employerField;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.BaseService;

@Service
public class employerFieldService{

	@Autowired
	private employerFieldRepository employerFieldRepository;
	

	public Long findIdbyCompanyFieldId(Long id)
	{
		return employerFieldRepository.findByCompanyFieldId(id);
	}
    public employerField getReferenceById(Long id)
	{
		return employerFieldRepository.getReferenceById(id);
	}
   
	public List<employerField> findAll()
	{
		return employerFieldRepository.findAll();
	}
	
	public employerField insert(employerField t)
	{
		return employerFieldRepository.save(t);
	}
	
	public employerField findById(Long id)
	{
		Optional<employerField> finded=employerFieldRepository.findById(id);
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
//		employerFieldRepository.updateEntity(t.getId(),t.getStatusCode()); 
//	}
	
	public employerField update(employerField t)
	{
		if(getReferenceById(t.getId())!=null)
		{
//			logInfo("Employer Updated Successfully");
			return employerFieldRepository.save(t);
		}else 
		{
//			logError("EmployerNotFound");
			return null;
			
		}
	}
	public List<employerField> insertAll(List<employerField> entity)
	{
		return employerFieldRepository.saveAll(entity);
	}
	
	public void deleteById(Long id)
	{
		employerField t=getReferenceById(id);
		if(t!=null)
		{
			employerFieldRepository.deleteById(id);
		}
	}
	
	
	public List<employerField> findAllEmployerFieldsWithId(Long id)
	{
		return employerFieldRepository.findByEmployerId(id);  
	}
	
}
