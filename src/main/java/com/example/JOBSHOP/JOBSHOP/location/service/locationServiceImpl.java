package com.example.JOBSHOP.JOBSHOP.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.location.location;

import jakarta.transaction.Transactional;

@Service
public class locationServiceImpl implements locationServiceInterface{

	@Autowired
	private locationRepository locationRepo;

	@Override
	public location findById(Long id) {
		return locationRepo.findById(id).get();
	}

	@Override
	public List<location> findAll() {
		return locationRepo.findAll();
	}

	@Override
	public List<String> findAllValues() {
		return locationRepo.findAllValues();
	}

	@Override
	public location insert(location loc) {
		 
		if(findByValue(loc.getLocationValue())!=null)
		{
			return null;
		}else
		{
			return locationRepo.save(loc);
		}
	}

	@Override
	public location update(location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		locationRepo.deleteById(id);
		
	}

	@Override
	public location findByValue(String locationValue) {
		return locationRepo.findByLocationValue(locationValue);
	}
	
	
}
