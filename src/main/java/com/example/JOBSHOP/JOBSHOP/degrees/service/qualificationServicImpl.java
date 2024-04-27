package com.example.JOBSHOP.JOBSHOP.degrees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.degrees.qualification;

import jakarta.transaction.Transactional;

@Service
public class qualificationServicImpl implements qualificationServiceInterface{

	@Autowired
	private qualificationRepository qualificationRepository;

	@Override
	public List<qualification> findByQualificationName(String qualificationName) {
		
		return qualificationRepository.findByQualificationNameLike(qualificationName);
	}

	@Override
	public qualification findByName(String qualificationName) {
		return qualificationRepository.findByQualificationName(qualificationName);
	}

	@Override
	public qualification insert(qualification qualification) {
		return qualificationRepository.save(qualification);
	}

	@Override
	public String deleteById(Long id) {
		
		if (findById(id).isPresent())
		{
			qualificationRepository.deleteById(id);
			return "deleted";
		}else 
		{
			return "not found";
		}
	}

	@Override
	public Optional<qualification> findById(Long id) {
		return qualificationRepository.findById(id);
	}

	@Override
	public List<qualification> findAll() {
		return qualificationRepository.findAll();
	}

	@Transactional
	@Override
	public String updateQualification(Long id, qualification qualification) {
		
		Optional<qualification> oldQualification=findById(id);
		
		if(oldQualification.isPresent())
		{
			qualification updated=oldQualification.get();
			if(qualification.getQualificationName()!=null)
			{
				updated.setQualificationName(qualification.getQualificationName());
				qualificationRepository.save(updated);
			}
			return "updated";
		}else 
		{
			return "not found";
		}
		
	}

	@Override
	public void insertAll(List<qualification> qualification) {
		
		qualificationRepository.saveAll(qualification);
		
	}

	@Override
	public List<String> findAllDistinct() {
		return qualificationRepository.findQualificationsDistinctNames();
	}
}
