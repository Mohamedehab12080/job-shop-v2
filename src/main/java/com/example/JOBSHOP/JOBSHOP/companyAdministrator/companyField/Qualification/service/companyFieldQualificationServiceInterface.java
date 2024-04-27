package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.service;

import java.util.List;


import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;


public interface companyFieldQualificationServiceInterface {
	Optional<companyFieldQualification> findByQualificationId(Long id);
	String insert(companyFieldQualification companyFieldQual);
	String update(Long id,companyFieldQualification companyFieldQual);
	String delete(Long id);
	List<companyFieldQualification> findByCompanyFieldId(Long companyFieldId);
	Optional<companyFieldQualification> findById(Long id);
	void insertAll(List<companyFieldQualification> companyFieldQualList);
	Optional<companyFieldQualification> findByQualificationIdAndCompanyFieldId(Long qualificationId,Long companyFieldId);
}
