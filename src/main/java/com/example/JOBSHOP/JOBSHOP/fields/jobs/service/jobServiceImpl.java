package com.example.JOBSHOP.JOBSHOP.fields.jobs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

@Service
public class jobServiceImpl implements jobServiceInterface{

	
	@Autowired
	private jobServiceRepository jobServiceRepository;
	
	@Override
	public jobModel findById(Long id) {
		if(jobServiceRepository.findById(id).isPresent())
		{
			return jobServiceRepository.findById(id).get();
		}else
		{
			return null;
		}
		
	}


	@Override
	public jobModel insert(jobModel entity) {
		return jobServiceRepository.save(entity);
	}

	@Override
	public void delete(Long id) {
		jobServiceRepository.deleteById(id);
	}


	@Override
	public List<jobModel> insertAll(List<jobModel> entity) {
		return jobServiceRepository.saveAll(entity);
	}

	@Override
	public jobModel findByName(String name) {
		return jobServiceRepository.findByName(name);
	}


	@Override
	public List<jobModel> findAll() {
		return jobServiceRepository.findAll();
	}


	@Override
	public List<String> findJobModelNames() {
		return jobServiceRepository.findJobModelNames();
	}

}
