package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.models.postField;
import com.example.JOBSHOP.JOBSHOP.repositories.companyFieldRepository;

import jakarta.transaction.Transactional;

@Service
public class companyFieldService{
	 @Autowired 
	 private companyFieldRepository companyFieldRepository;
	 
	 @Autowired
	 private postFieldService postFieldService;
	 
	 public postField findPostFieldWithFieldName(String fieldName)
	 {
		 return postFieldService.findByEmployerField(companyFieldRepository.findByFieldName(fieldName));
	 }
	     
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
	 
	 /**
	  * 
	  * @author BOBO 
	  * @function update any thing in field except companyAdministrators
	  */
	 @Transactional
	 public companyField updateCompanyField(companyField newCompanyField)
	 {
		 companyField oldCompanyField=getReferenceById(newCompanyField.getId());
		 
		 if(newCompanyField.getFieldName()!=null)
		 {
			 oldCompanyField.setFieldName(newCompanyField.getFieldName());
		 }
		 if(newCompanyField.getSkills()!=null)
		 {
			 oldCompanyField.setSkills(newCompanyField.getSkills());
		 }
		 if(newCompanyField.getRequiredQualifications()!=null)
		 {
			 oldCompanyField.setRequiredQualifications(newCompanyField.getRequiredQualifications());
		 }
		 return companyFieldRepository.save(oldCompanyField); 
	 }
	 /**
	  * 
	  * @author BOBO 
	  * @function find company Fields With companyAdmin Id .
	  */
	 public List<companyField> findCompanyFieldsWithAdminId(Long Id)
	 {
		 return companyFieldRepository.findByCompanyAdministratorId(Id);
	 }
}
