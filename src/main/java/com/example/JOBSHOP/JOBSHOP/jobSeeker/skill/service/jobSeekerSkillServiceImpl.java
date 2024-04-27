package com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;

import jakarta.transaction.Transactional;

@Service
public class jobSeekerSkillServiceImpl implements jobSeekerSkillServiceInterface{

	@Autowired
	private jobSeekerSkillRepository jobSeekerSkillRepo;
	
	@Override
	public jobSeekerSkill getReferenceById(Long id) {
		return jobSeekerSkillRepo.getReferenceById(id);
	}

	@Override
	public List<jobSeekerSkill> findByJobSeekerId(Long id) {
		return jobSeekerSkillRepo.findByjobSeekerId(id);
	}

	@Override
	public jobSeekerSkill insert(jobSeekerSkill entity) {
		
		Optional <jobSeekerSkill> search=findByJobSeekerIdAndSkillId(entity.getJobSeeker().getId(),entity.getSkill().getId());
		if(search.isPresent())
		{
			return null;
		}else 
		{
			return jobSeekerSkillRepo.save(entity);
		}
	}

	@Override
	public Optional<jobSeekerSkill> findById(Long id) {
		
		return jobSeekerSkillRepo.findById(id);
	}

	@Override
	public List<jobSeekerSkill> insertAll(List<jobSeekerSkill> skillsList) {
		
		return jobSeekerSkillRepo.saveAll(skillsList);
	}

	@Transactional
	@Override
	public String deleteById(Long id) {
		
		
		if(findById(id).isPresent())
		{
			jobSeekerSkillRepo.deleteById(id);
			return "deleted";
		}else 
		{
			return "skill not found";
		}
	}

	@Override
	public Optional<jobSeekerSkill> findByJobSeekerIdAndSkillId(Long jobSeekerId, Long skillId) {
		
		return jobSeekerSkillRepo.findByJobSeekerIdAndSkillId(jobSeekerId, skillId);
	}

	

}
