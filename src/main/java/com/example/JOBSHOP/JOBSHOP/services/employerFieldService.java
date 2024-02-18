package com.example.JOBSHOP.JOBSHOP.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.employerField;
import com.example.JOBSHOP.JOBSHOP.repositories.employerFieldRepository;

@Service
public class employerFieldService {

	@Autowired
	private employerFieldRepository employerFieldRepository;
	
	public List<employerField> findAllEmployerFieldsWithId(Long id)
	{
		return employerFieldRepository.findByEmployerId(id); 
	}
	
}
