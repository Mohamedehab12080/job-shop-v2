package com.example.JOBSHOP.JOBSHOP.Employer.employerField.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;

public interface employerFieldServiceInterface {

//	Long findIdbyCompanyFieldId(Long id);

	List<employerField> findAllEmployersFieldsByCompanyFieldId(Long id);
	employerField getReferenceById(Long id);

	List<employerField> findAll();

	employerField insert(employerField t);

	employerField findById(Long employerId,Long fieldId);

//	employerField update(Long id,employerField t);

	List<employerField> insertAll(List<employerField> entity);

	List<employerField> findAllEmployerFieldsWithId(Long id);
	void deleteById(Long id);

}
