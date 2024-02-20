package com.example.JOBSHOP.JOBSHOP.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.models.employerProfile;
import com.example.JOBSHOP.JOBSHOP.repositories.employerProfileRepository;

@Service
public class employerProfileService {

	
	@Autowired
	private employerProfileRepository employerProfileRepository;
	
	public employerProfile findById(Long id)
	{
		return employerProfileRepository.findById(id).get();
	}
	
	public employerProfile findByEmployer(Long id)
	{
		return employerProfileRepository.findByEmployerId(id).get();
	}
	
	public void insert(employerProfile employerProfile)
	{
		employerProfileRepository.save(employerProfile);
	}
}
