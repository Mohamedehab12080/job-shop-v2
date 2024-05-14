package com.example.JOBSHOP.JOBSHOP.fields.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.fields.Field;

public interface fieldServiceInterface {

	
	List<Field> findByFieldName(String fieldName);
	
	Field findByName(String fieldName);
	
	int insert(Field field);
	
	Field insertForCompanyOperation(Field field);
	
	void deleteById(Long id);
	
	Optional<Field> findById(Long id);
	
	List<String>findAllDistinct();
	void updateSkill(Long id,Field field);
	void insertAll(List<Field> fieldsToInsert);
	
	Long findIdByName(String fieldName);
}
