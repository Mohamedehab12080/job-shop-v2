package com.example.JOBSHOP.JOBSHOP.Application.skill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;

import jakarta.transaction.Transactional;

@Service
public class 
applicationSkillServiceImpl 
implements applicationSkillServiceInterface{

	@Autowired
	private applicationSkillRepository applicationSkillRepository;

	
	@Override
	@Transactional
	public List<applicationSkill> insertAll(List<applicationSkill> appSkillList) {
		return applicationSkillRepository.saveAll(appSkillList);
	}

	@Override
	public applicationSkill insert(applicationSkill appSkill) {
		return applicationSkillRepository.save(appSkill);
	}

	@Override
	@Transactional
	public applicationSkill update(Long id, applicationSkill appSkill) {
	
		applicationSkill old=findById(id);
		if(old!=null)
		{
			return applicationSkillRepository.save(old);
		}else 
		{
			return null;
		}
		
	}

	@Override
	public String deleteById(Long id) {
		applicationSkill obj=findById(id);
		if(obj!=null)
		{
			 applicationSkillRepository.deleteById(id);
			return "deleted";
		}else 
		{
			return "not found";
		}
	}

	@Override
	public List<applicationSkill> findByApplicationId(Long appId) {
		
		return applicationSkillRepository.findByApplicationId(appId);
	}

	@Override
	public applicationSkill findById(Long id) {
		
		return applicationSkillRepository.findById(id).get();
	}

}
