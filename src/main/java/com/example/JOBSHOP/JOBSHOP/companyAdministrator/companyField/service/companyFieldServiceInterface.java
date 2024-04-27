package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;

public interface companyFieldServiceInterface {

    companyField getReferenceById(Long id);
	 List<companyField> findAll();
	 companyField insert(companyField t);
	 companyField findById(Long id);
	 List<companyField> insertAll(List<companyField> entity);
	 String deleteById(Long id);
	 String updateCompanyField(Long id,companyField newCompanyField);
	 List<companyField> findCompanyFieldsWithAdminId(Long Id);
	companyField findByFieldName(String fieldName);
	Long findIdByFieldName(String fieldName);
	companyField insertCompanyFieldAndSkillsAndQualifications(Long companyAdminId,companyFieldDTO dto);
	companyField insertCompanyFieldAndSkillsAndQualifications(companyFieldDTO companyFieldDto);

}
