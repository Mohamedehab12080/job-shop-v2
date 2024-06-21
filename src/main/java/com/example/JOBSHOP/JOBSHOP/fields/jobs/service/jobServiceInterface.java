package com.example.JOBSHOP.JOBSHOP.fields.jobs.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

public interface jobServiceInterface {

	
	public jobModel findById(Long id);
	public jobModel findByName(String name);
	public jobModel insert(jobModel entity);
	public List<jobModel> findAll();
	public void delete(Long id);
	List<jobModel> insertAll(List<jobModel> entity);
	List<String>findJobModelNames();
	
}
