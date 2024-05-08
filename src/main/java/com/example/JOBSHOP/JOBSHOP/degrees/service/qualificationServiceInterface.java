package com.example.JOBSHOP.JOBSHOP.degrees.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;

public interface qualificationServiceInterface {

	
	List<Qualification> findByQualificationName(String qualificationName);
	Qualification findByName(String qualificationName);
	Qualification insert(Qualification qualification);
	String deleteById(Long id);
	Optional<Qualification> findById(Long id);
	List<Qualification>findAll();
	List<String> findAllDistinct();
	String updateQualification(Long id,Qualification qualification);
	void insertAll(List<Qualification> qualification);
}
