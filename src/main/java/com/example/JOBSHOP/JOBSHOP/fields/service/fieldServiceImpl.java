package com.example.JOBSHOP.JOBSHOP.fields.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.fields.Field;

import jakarta.transaction.Transactional;

@Service
public class fieldServiceImpl implements fieldServiceInterface{

	@Autowired
	private fieldRepository fieldRepository;
	
	@Override
	public List<Field> findByFieldName(String fieldName) {
		return fieldRepository.findByFieldNameList(fieldName);
	}

	@Override
	public Field findByName(String fieldName) {
		return fieldRepository.findByFieldName(fieldName);
	}

	@Override
	public int insert(Field field) {
		Field f=fieldRepository.save(field);
		if(f!=null)
		{
			return 1;
		}else 
		{
			return 0;
		}
	}

	@Override
	public Field insertForCompanyOperation(Field field) {
		return fieldRepository.save(field);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		fieldRepository.deleteById(id);	
	}

	@Override
	public Optional<Field> findById(Long id) {
		Optional <Field> oField=fieldRepository.findById(id);
		if(oField.isPresent())
		{
			return oField;
		}else 
		{
			return null;
		}
	}

	@Override
	public List<String> findAllDistinct() {
		return fieldRepository.findAllDistinctFieldNames();
	}

	@Transactional
	@Override
	public void updateSkill(Long id, Field field) {
		if(findById(id)!=null)
		{
			Field fieldForUpdate=findById(id).get(); 
			if(field.getFieldName()!=null)
			{
				fieldForUpdate.setFieldName(field.getFieldName());
			}
			fieldRepository.save(fieldForUpdate);
		}
	}

	@Override
	public void insertAll(List<Field> fieldsToInsert) {
		fieldRepository.saveAll(fieldsToInsert);
	}

	@Override
	public Long findIdByName(String fieldName) {
		return fieldRepository.findIdByName(fieldName);
	}

	
}
