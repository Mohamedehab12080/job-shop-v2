package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.companyField;
import com.example.JOBSHOP.JOBSHOP.repositories.companyFieldRepository;

@Service
public class companyFieldService {
	 @Autowired 
	 private companyFieldRepository companyFieldRepository;
	 
	 
	 public companyField insertCompanyField (companyField companyField)
	 {
		 return companyFieldRepository.save(companyField);
	 }
	 public List<companyField> findCompanyFieldsWithAdminId(Long Id)
	 {
		 return companyFieldRepository.findByCompanyAdministratorId(Id);
	 }
}
