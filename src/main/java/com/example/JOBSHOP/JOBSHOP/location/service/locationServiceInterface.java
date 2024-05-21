package com.example.JOBSHOP.JOBSHOP.location.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.location.location;

public interface locationServiceInterface {

	location findById(Long id);
	List<location>findAll();
	List<String>findAllValues();
	location insert(location loc);
	location update(location loc);
	void delete(Long id);
	location findByValue(String locationValue);
	
}
