package com.example.JOBSHOP.JOBSHOP.degrees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;

import jakarta.transaction.Transactional;

@Service
public class qualificationServicImpl implements qualificationServiceInterface{

	@Autowired
	private qualificationRepository qualificationRepository;

	@Override
	public List<Qualification> findByQualificationName(String qualificationName) {
		
		return qualificationRepository.findByQualificationNameLike(qualificationName);
	}

	@Override
	public Qualification findByName(String qualificationName) {
		return qualificationRepository.findByQualificationName(qualificationName);
	}

	@Override
	public Qualification insert(Qualification qualification) {
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
	public Optional<Qualification> findById(Long id) {
		return qualificationRepository.findById(id);
	}

	@Override
	public List<Qualification> findAll() {
		return qualificationRepository.findAll();
	}

	@Transactional
	@Override
	public String updateQualification(Long id, Qualification qualification) {
		
		Optional<Qualification> oldQualification=findById(id);
		
		if(oldQualification.isPresent())
		{
			Qualification updated=oldQualification.get();
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
	public void insertAll(List<Qualification> qualification) {
		
		qualificationRepository.saveAll(qualification);
		
	}

	@Override
	public List<String> findAllDistinct() {
		return qualificationRepository.findAllQualificationName();
	}
}
