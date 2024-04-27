package com.example.JOBSHOP.JOBSHOP.degrees.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.degrees.qualification;

public interface qualificationServiceInterface {

	
	List<qualification> findByQualificationName(String qualificationName);
	qualification findByName(String qualificationName);
	qualification insert(qualification qualification);
	String deleteById(Long id);
	Optional<qualification> findById(Long id);
	List<qualification>findAll();
	List<String> findAllDistinct();
	String updateQualification(Long id,qualification qualification);
	void insertAll(List<qualification> qualification);
}
