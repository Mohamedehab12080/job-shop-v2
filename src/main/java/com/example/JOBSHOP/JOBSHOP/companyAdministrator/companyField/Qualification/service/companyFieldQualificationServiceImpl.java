package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.service;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;

@Service
public class companyFieldQualificationServiceImpl implements companyFieldQualificationServiceInterface{

	@Autowired
	private companyFieldQualificationRepository companyFieldQualificationRepository;

	@Override
	public Optional<companyFieldQualification> findByQualificationId(Long id) {
		return companyFieldQualificationRepository.findByQualificationId(id);
	}

	@Override
	public String insert(companyFieldQualification companyFieldQual) {
		
		Optional<companyFieldQualification>companyFieldQualification=
				findByQualificationIdAndCompanyFieldId(
				companyFieldQual.getQualification().getId(),
				companyFieldQual.getCompanyField().getId());
		
		if(!companyFieldQualification.isPresent())
		{
			companyFieldQualificationRepository.save(companyFieldQual);
			return "added";
		}else 
		{
			return "qualification : "+companyFieldQualification.get().getQualification().getQualificationName()+" already exist";
		}
	}

	@Override
	public String update(Long id, companyFieldQualification companyFieldQual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Long id) {
		
		Optional<companyFieldQualification> cQual =findById(id);
		if(cQual.isPresent())
		{
			companyFieldQualificationRepository.deleteById(id);
			return "deleted";
		}else 
		{
			return "not found";
		}
	}

	@Override
	public List<companyFieldQualification> findByCompanyFieldId(Long companyFieldId) {
		return companyFieldQualificationRepository.findByCompanyFieldId(companyFieldId);
	}

	@Override
	public Optional<companyFieldQualification> findById(Long id) {
		return companyFieldQualificationRepository.findById(id);
	}

	@Override
	public void insertAll(List<companyFieldQualification> companyFieldQualList) {
		
		companyFieldQualificationRepository.saveAll(companyFieldQualList);
	
	}

	@Override
	public Optional<companyFieldQualification> findByQualificationIdAndCompanyFieldId(Long qualificationId,
			Long companyFieldId) {
		
		return companyFieldQualificationRepository.findByQualificationIdAndCompanyFieldId(qualificationId,companyFieldId);
	}
	
	
	
}
