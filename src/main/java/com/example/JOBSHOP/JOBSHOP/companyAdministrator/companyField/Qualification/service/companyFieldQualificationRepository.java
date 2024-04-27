package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;


@Repository
public interface companyFieldQualificationRepository extends JpaRepository<companyFieldQualification, Long>{

List<companyFieldQualification> findByCompanyFieldId(Long id);
	
Optional<companyFieldQualification> findByQualificationId(Long id); 

Optional<companyFieldQualification> findByQualificationIdAndCompanyFieldId(Long qualificationId,Long companyFieldId);

}
	