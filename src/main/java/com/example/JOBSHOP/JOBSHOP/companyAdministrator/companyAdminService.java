package com.example.JOBSHOP.JOBSHOP.companyAdministrator;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.BaseService;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.employerRepository;
import com.example.JOBSHOP.JOBSHOP.Employer.employerService;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfileRepository;
import com.example.JOBSHOP.JOBSHOP.companyProfile.companyProfileService;
import com.example.JOBSHOP.JOBSHOP.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.employerField.employerFieldRepository;
import com.example.JOBSHOP.JOBSHOP.employerField.employerFieldService;
import com.example.JOBSHOP.JOBSHOP.employerProfile.employerProfile;
import com.example.JOBSHOP.JOBSHOP.employerProfile.employerProfileService;

import jakarta.transaction.Transactional;

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
	 
	 
	 public companyProfile findcompanyProfileIdByCompanyName(String companyName)
	 {
		 return companyProfileService.findByCompanyAdmin(companyAdminRepository.findByCompanyName(companyName));
	 }
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
		
		/**
		 * 
		 * @author BOB 
		 * @Fucntion update companyAdministrator (companyName,userName,Password,Email,Contacts)
		 */
		@Transactional
		public companyAdministrator update(companyAdministrator newCompanyAdministrator)
		{
			companyAdministrator oldCompanyAdministrator=getReferenceById(newCompanyAdministrator.getId());
			if(oldCompanyAdministrator!=null)
			{
				if(newCompanyAdministrator.getCompanyName()!=null)
				{
					oldCompanyAdministrator.setCompanyName(newCompanyAdministrator.getCompanyName()); 
				} 
			   if(newCompanyAdministrator.getPassword()!=null)
				{
					oldCompanyAdministrator.setPassword(newCompanyAdministrator.getPassword()); 
				}
			   if(newCompanyAdministrator.getContacts()!=null)
				{
					oldCompanyAdministrator.setContacts(newCompanyAdministrator.getContacts()); 
				}
			    if(newCompanyAdministrator.getEmail()!=null)
				{
			    	oldCompanyAdministrator.setEmail(newCompanyAdministrator.getEmail()); 
				} 
			    if(newCompanyAdministrator.getUserName()!=null)
				{
			    	oldCompanyAdministrator.setUserName(newCompanyAdministrator.getUserName()); 
				} 
				
//				logInfo("Employer Updated Successfully");
				return companyAdminRepository.save(oldCompanyAdministrator);
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
			 employerFieldService.insertAll(batch);
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
		 Employer employer=employerService.findById(id);
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
			companyAdministrator companyAdminUpdate=companyAdminRepository.getReferenceById(id);
			 if(companyAdminUpdate!=null)
			 {
				 companyAdministrator companyAdminForUpdate=companyAdminUpdate;
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