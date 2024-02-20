package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.repositories.companyFieldRepository;

@Service
public class companyFieldService{
	 @Autowired 
	 private companyFieldRepository companyFieldRepository;
	 
	    public companyField getReferenceById(Long id)
		{
			return companyFieldRepository.getReferenceById(id);
		}
	   
		public List<companyField> findAll()
		{
			return companyFieldRepository.findAll();
		}
		
		public companyField insert(companyField t)
		{
			return companyFieldRepository.save(t);
		}
		
		public companyField findById(Long id)
		{
			Optional<companyField> finded=companyFieldRepository.findById(id);
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
//			applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		
		public companyField update(companyField t)
		{
			if(getReferenceById(t.getId())!=null)
			{
//				logInfo("Employer Updated Successfully");
				return companyFieldRepository.save(t);
			}else 
			{
//				logError("EmployerNotFound");
				return null;
				
			}
		}
		public List<companyField> insertAll(List<companyField> entity)
		{
			return companyFieldRepository.saveAll(entity);
		}
		
		public void deleteById(Long id)
		{
			companyField t=getReferenceById(id);
			if(t!=null)
			{
				companyFieldRepository.deleteById(id);
			}
		}
		
	 public companyField insertCompanyField (companyField companyField)
	 {
		 return companyFieldRepository.save(companyField);
	 }
	 
	 public List<companyField> findCompanyFieldsWithAdminId(Long Id)
	 {
		 return companyFieldRepository.findByCompanyAdministratorId(Id);
	 }
}
