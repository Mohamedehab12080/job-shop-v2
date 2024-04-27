package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;

@Service
public class employerProfileServiceImpl implements employerProfileServiceInterface{

	
	@Autowired
	private employerProfileRepository employerProfileRepository;
	
	@Override
	public employerProfile findById(Long id)
	{
		return employerProfileRepository.findById(id).get();
	}
	
	@Override
	public employerProfile findByEmployer(Long id)
	{
		return employerProfileRepository.findByEmployerId(id).get();
	}
	
	@Override
	public void insert(employerProfile employerProfile)
	{
		employerProfileRepository.save(employerProfile);
	}
}
