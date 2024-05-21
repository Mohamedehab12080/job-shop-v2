package com.example.JOBSHOP.JOBSHOP.companyAdministrator.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.service.employerFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;
import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.service.employerProfileServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.registerUserRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.service.companyProfileService;

import jakarta.transaction.Transactional;

@Service
public class companyAdminService implements companyAdministratorServiceInterface {

	
	 @Autowired
	 private companyAdminRepository companyAdminRepository;
	 
	 @Autowired
	 private companyProfileService companyProfileService; 
	 
	 @Autowired
	 private employerServiceInterface employerService;
	 
	 @Autowired
	 private employerFieldServiceInterface employerFieldService;

	 @Autowired
	 private employerProfileServiceInterface employerProfileService;
	 
	 @Autowired
	 private postServiceInterface postServiceInterface;
	 
	 

	 public companyAdministrator findByUserName(String userName)
	 {
		return companyAdminRepository.findByUserName(userName); 
	 }
	 
	 @Override
	 public Optional<companyAdministrator> findByEmail(String email)
	 {
		return companyAdminRepository.findByEmail(email); 
	 }
	 
	 @Override
	 public companyProfile findcompanyProfileIdByCompanyName(String companyName) throws UserException
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
		
		
		@Override
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
		@Override
		public companyAdministratorDTO update(Long id,registerUserRequest req)
		{
			companyAdministrator old=getReferenceById(id);
			if(old!=null)
			{
				
				if(req.getUserName()!=null)
				{
					old.setUserName(req.getUserName());
				}
				
				if(req.getCompanyName()!=null)
				{
					old.setCompanyName(req.getCompanyName()); 
				} 
				
				if(req.getAddress() !=null)
				{
					old.setAddress(req.getAddress());
				}
			    
				if(req.getDescription() !=null)
				{
					old.setDescription(req.getDescription());
				}
				
			    if(req.getPicture()!=null)
			    {
			    	old.setCoverImage(req.getCoverImage());
			    }
			    
			    if(req.getPicture() != null)
			    {
			    	old.setPicture(req.getPicture());
			    }
			    companyAdministrator inserted= companyAdminRepository.save(old);
			    companyAdministratorDTO dto=companyAdministratorMapper.mapCompanyAdminToDTO(old);
			    dto.setReq_user(true);
				return dto;
			}
			
			else 
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
	 
	 public List<employerField> giveEmployerFieldsSaveAll(List<employerField> employerFields)
	 {
		 return employerFieldService.insertAll(employerFields);
	 }
	 
	 public void giveEmployerFields(List<employerField> employerFields,int batchSize)
	 {
		 
		 for (int i = 0; i < employerFields.size(); i++) {
			 int endIndex=Math.min(i+batchSize, employerFields.size());
			 List<employerField> batch=employerFields.subList(i, endIndex);
			 employerFieldService.insertAll(batch);
		}
		 
	 }
	
	 
	 @Override
	 public companyAdministrator insert(companyAdministrator company)
	 {
		 if(company!=null)
		 {
			 companyProfile companypro=new companyProfile();
			 companypro.setCompanyAdmin(company);
			 companyAdministrator companyAdmins=companyAdminRepository.save(company);
			 companyProfileService.insert(companypro);
			 if(companyAdmins!=null)
			 {
				 return companyAdmins;
			 }else 
			 {
				 return null;
			 }
		 }else 
		 {
			 return null;
		 }
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
	 public companyAdministrator insertPicture(Long id,String picture)
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