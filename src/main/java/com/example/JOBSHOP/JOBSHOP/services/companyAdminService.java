package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.repositories.companyAdminRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.companyFieldRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.companyProfileRepository;
import com.example.JOBSHOP.JOBSHOP.repositories.employerFieldRepository;

@Service
public class companyAdminService extends BaseService{

	
	 @Autowired
	 private companyAdminRepository companyAdminRepository;
	 
	 @Autowired 
	 private companyProfileRepository companyProfileRepository;
	 
	 @Autowired
	 private employerFieldRepository employerFieldRepository;
	
	 public employerField giveEmployerField(employerField employerField)
	 {
		 return employerFieldRepository.save(employerField);
	 }
	 public void giveEmployerFields(List<employerField> employerFields,int batchSize)
	 {
		 for (int i = 0; i < employerFields.size(); i++) {
			
			 int endIndex=Math.min(i+batchSize, employerFields.size());
			 List<employerField> batch=employerFields.subList(i, endIndex);
			 employerFieldRepository.saveAll(batch);
		}
		 
	 }
	 public companyAdministrator insert(companyAdministrator company)
	 {
		 return companyAdminRepository.save(company);
	 }
	 public companyProfile insertcompanyProfile(companyProfile company)
	 {
		 return companyProfileRepository.save(company);
	 }
	 public List<companyAdministrator> findAll()
	 {
		return companyAdminRepository.findAll();
	 }
	 public companyAdministrator getCompanyAdminById(Long id)
	 {
		 return companyAdminRepository.findById(id).get();
	 }
	 public void jobSeeker(String name)
	 {
		 try {
			 companyAdministrator companyAdmin=new companyAdministrator();
			 companyAdmin.setCompanyName("MO_Software");
			 companyAdminRepository.save(companyAdmin);
			logInfo(name+": created successfully"); 
		} catch (Exception e) {
			handleException(e); 
		}
	 }
}