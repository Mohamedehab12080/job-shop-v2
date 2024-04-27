package com.example.JOBSHOP.JOBSHOP.Application.qualification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;

import jakarta.transaction.Transactional;

@Service
public class applicaitonQualificationServiceImpl implements applicationQualificationInterface {

	@Autowired
	private applicationQualificationRepository applicationQualificationRepository;
	
	@Override
	@Transactional
	public List<applicationQualification> insertAll(List<applicationQualification> applicationQualification) {
		
		return applicationQualificationRepository.saveAll(applicationQualification);
	}

	@Override
	public applicationQualification insert(applicationQualification applicationQualification) {
		return applicationQualificationRepository.save(applicationQualification);
	}

	@Override
	@Transactional
	public applicationQualification update(Long id ,applicationQualification applicationQualification) {
		applicationQualification old=findById(id);
		return applicationQualificationRepository.save(old);
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<applicationQualification> findByApplicationId(Long applicationId) {
		return applicationQualificationRepository.findByApplicationId(applicationId);
	}

	@Override
	public applicationQualification findById(Long id) {
		return applicationQualificationRepository.findById(id).get();
	}

}
