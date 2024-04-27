package com.example.JOBSHOP.JOBSHOP.Application.qualification.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;

public interface applicationQualificationInterface {

	List<applicationQualification> insertAll(List<applicationQualification> applicationQualification);
	applicationQualification insert(applicationQualification applicationQualification);
	applicationQualification update(Long id,applicationQualification applicationQualification);
	String deleteById(Long id);
	List<applicationQualification> findByApplicationId(Long applicationId);
	applicationQualification findById(Long id);
}
