package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.models.employerProfile;
import com.example.JOBSHOP.JOBSHOP.repositories.companyAdminRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.companyProfileRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.employerFieldRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.employerRepository;

@Service
public class companyAdminService {

	
	 @Autowired
	 private companyAdminRepository companyAdminRepository;
	 
	 @Autowired
	 private companyProfileService companyProfileService; 
	 
	 @Autowired
	 private employerService employerService;
	 
	 @Autowired
	 private employerFieldService employerFieldService;

	 @Autowired
	 private employerProfileService employerProfileService;
	 
	 
	 public Employer createEmployer(Employer employer)
	 {
		employerProfile employerProfile=new employerProfile();
		employerProfile.setEmployer(employer);
		Employer employerSaved=employerService.insert(employer);
		employerProfileService.insert(employerProfile);
		return employerSaved;
	 }
	    public companyAdministrator getReferenceById(Long id)
		{
			return companyAdminRepository.getReferenceById(id);
		}
	   
		public List<companyAdministrator> findAll()
		{
			return companyAdminRepository.findAll();
		}
		
		
		public companyAdministrator findById(Long id)
		{
			Optional<companyAdministrator> finded=companyAdminRepository.findById(id);
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
		
		public companyAdministrator update(companyAdministrator t)
		{
			if(getReferenceById(t.getId())!=null)
			{
//				logInfo("Employer Updated Successfully");
				return companyAdminRepository.save(t);
			}else 
			{
//				logError("EmployerNotFound");
				return null;
				
			}
		}
		public List<companyAdministrator> insertAll(List<companyAdministrator> entity)
		{
			return companyAdminRepository.saveAll(entity);
		}
		
		public void deleteById(Long id)
		{
			companyAdministrator t=getReferenceById(id);
			if(t!=null)
			{
				companyAdminRepository.deleteById(id);
			}
		}

	 
	 public employerField giveEmployerField(employerField employerField)
	 {
		 return employerFieldService.insert(employerField); 
	 }
	 
	 public void giveEmployerFields(List<employerField> employerFields,int batchSize)
	 {
		 for (int i = 0; i < employerFields.size(); i++) {
			
			 int endIndex=Math.min(i+batchSize, employerFields.size());
			 List<employerField> batch=employerFields.subList(i, endIndex);
			 employerFieldService.insertAll(employerFields);
		}
		 
	 }
	
	 
	 public companyAdministrator insert(companyAdministrator company)
	 {
		 companyProfile companypro=new companyProfile();
		 companypro.setCompanyAdmin(company);
		 companyAdministrator companyAdmins=companyAdminRepository.save(company);
		 companyProfileService.insert(companypro);
		 return companyAdmins;
	 }
	 
	 
//	 public int deleteEmployer(Long empId)
//	 {
//		 Optional<Employer> employer=employerRepository.findById(empId);
//		 if(employer.isPresent())
//		 {
//			 return employerRepository.deleteEmployer(empId);
//		 }else 
//		 {
//			 return 0;
//		 }
//		 
//	 }
	 
	 public int deleteEmployerWithId(Long id)
	 { 
		 Employer employer=employerService.getReferenceById(id);
		 if(employer!=null)
		 {
//			 employerRepository.delete(employer);
			 employerService.deleteById(id);
			 return 1;
		 }else 
		 { 
			 return 0;
		 }
	 } 
	 public companyAdministrator insertPicture(Long id,byte[] picture)
	 {
		try {
			Optional<companyAdministrator> companyAdminUpdate=companyAdminRepository.findById(id);
			 if(companyAdminUpdate.isPresent())
			 {
				 System.out.println("Job Seeker :" +companyAdminUpdate.get().getEmail());
				 companyAdministrator companyAdminForUpdate=companyAdminUpdate.get();
				 companyAdminForUpdate.setPicture(picture);
				 return companyAdminRepository.save(companyAdminForUpdate);
			 }else  
			 {
				 return null;
			 }
		} catch (Exception e) {
			return null;
		}
	 }
	 
}