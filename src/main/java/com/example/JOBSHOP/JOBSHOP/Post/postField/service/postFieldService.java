package com.example.JOBSHOP.JOBSHOP.Post.postField.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.service.employerFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;

import jakarta.transaction.Transactional;

@Service
public class postFieldService implements postFieldServiceInterface{

	@Autowired 
	private postFieldRepository postFieldRepository;
	
	@Autowired
	private employerFieldServiceInterface employerFieldService;
	
//	@Override
//	public postField findByEmployerField(Long id)
//	{
//		return postFieldRepository.findByEmployerFieldId(employerFieldService.findIdbyCompanyFieldId(id));
//	}
//	
	@Override
    public postField getReferenceById(Long id)
	{
		return postFieldRepository.getReferenceById(id);
	}
   
	@Override
	public List<postField> findAll()
	{
		return postFieldRepository.findAll();
	}
	
	@Override
	public postField insert(postField t)
	{
		return postFieldRepository.save(t);
	}
	
	@Override
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
	
	@Override
	@Transactional
	public postField update(postField t)
	{
		postField postfield=getReferenceById(t.getId());
		if(postfield!=null)
		{
			if(t.getField()!=null)
			{
				postfield.setField(t.getField());
			}
			
			if(t.getQualifications()!=null && !t.getSkills().isEmpty())
			{
				postfield.setQualifications(t.getQualifications());
			}
			
			if(t.getSkills()!=null && !t.getSkills().isEmpty())
			{
				postfield.setSkills(t.getSkills());
			}
			postFieldRepository.save(postfield);
			return postfield;
		}else 
		{
			return null;
			
		}
	}
	
	@Override
	@Transactional
	public List<postField> insertAll(List<postField> entity)
	{
		return postFieldRepository.saveAll(entity);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id)
	{
		postField t=getReferenceById(id);
		if(t!=null)
		{
			postFieldRepository.deleteById(id);
		}
	}

//	@Override
//	public List<postField> findAllPostFieldsByEmployerFieldId(Long empFieldId) {
//		return postFieldRepository.findByEmployerFieldId(empFieldId);
//	}
}
