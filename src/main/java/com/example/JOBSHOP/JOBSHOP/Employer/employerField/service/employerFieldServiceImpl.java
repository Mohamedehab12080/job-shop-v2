package com.example.JOBSHOP.JOBSHOP.Employer.employerField.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;

import jakarta.transaction.Transactional;

@Service
public class employerFieldServiceImpl implements employerFieldServiceInterface{

	@Autowired
	private employerFieldRepository employerFieldRepository;
	
//
//	@Override
//	public Long findIdbyCompanyFieldId(Long id)
//	{
//		return employerFieldRepository.findByCompanyFieldId(id);
//	}
	@Override
    public employerField getReferenceById(Long id)
	{
		return employerFieldRepository.getReferenceById(id);
	}
	@Override
	public List<employerField> findAll()
	{
		return employerFieldRepository.findAll();
	}
	@Override
	public employerField insert(employerField t)
	{
		return employerFieldRepository.save(t);
	}
	
	@Override
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
	
	
	
	@Override
	public List<employerField> insertAll(List<employerField> entity)
	{
		return employerFieldRepository.saveAll(entity);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id)
	{
		employerField t=getReferenceById(id);
		if(t!=null)
		{
			employerFieldRepository.deleteById(id);
		}
	}
	
	@Override
	public List<employerField> findAllEmployerFieldsWithId(Long id)
	{
		return employerFieldRepository.findByEmployerId(id);  
	}
	
	
	@Override
	public List<employerField> findAllEmployersFieldsByCompanyFieldId(Long id) {
		return employerFieldRepository.findByCompanyFieldId(id);
	}
	
}
