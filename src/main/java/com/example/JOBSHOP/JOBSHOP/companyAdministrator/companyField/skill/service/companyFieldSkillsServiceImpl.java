package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;

import jakarta.transaction.Transactional;

@Service
public class companyFieldSkillsServiceImpl implements companyFieldSkillsServiceInterface{

	@Autowired 
	private companyFieldSkillsRepository companyFieldSkillRepository;
	
	@Override
	public String insert(companyFieldSkill companyFieldSkill) {
		
		Optional<companyFieldSkill> companyfieldSkill=findBySkillId(companyFieldSkill.getCompanyFieldSkill().getId());
		if(!companyfieldSkill.isPresent())
		{
			companyFieldSkillRepository.save(companyFieldSkill);
			return "added";
		}else 
		{
			return "Skill : "+companyFieldSkill.getCompanyFieldSkill().getSkillName()+" already exist";
		}
		
	}

	@Transactional
	@Override
	public String update(Long id,companyFieldSkill companyFieldSkill) {
		
		Optional<companyFieldSkill> companyfieldSkill=findById(id);
		companyFieldSkill forUpdate=companyfieldSkill.get();
		if(companyfieldSkill.isPresent())
		{
			if(companyFieldSkill.getCompanyField()!=null)
			{
				forUpdate.setCompanyField(companyFieldSkill.getCompanyField());
			}
			if(companyFieldSkill.getCompanyFieldSkill()!=null)
			{
				forUpdate.setCompanyFieldSkill(companyFieldSkill.getCompanyFieldSkill());
			}
			companyFieldSkillRepository.save(forUpdate);
			return "updated";
		}else 
		{
			return "can't be updated because the skill not found";
		}
		
	}

	@Transactional
	@Override
	public String delete(Long id) {
		Optional<companyFieldSkill> companyfieldSkill=findById(id);
		if(companyfieldSkill.isPresent())
		{
			companyFieldSkillRepository.deleteById(id);
			return "deleted";
		}else 
		{
			return "skill not found!";
		}
	}

	@Override
	public List<companyFieldSkill> findByCompanyFieldId(Long companyFieldId) {
		return companyFieldSkillRepository.findByCompanyFieldId(companyFieldId);
	}

	@Override
	public Optional<companyFieldSkill> findById(Long id) {
		
		return companyFieldSkillRepository.findById(id);
	}

	@Override
	public Optional<companyFieldSkill> findBySkillId(Long id) {
		return companyFieldSkillRepository.findByCompanyFieldSkillId(id);
	}

	@Override
	public void insertAll(List<companyFieldSkill> companyFieldSkills) {
		companyFieldSkillRepository.saveAll(companyFieldSkills);
	}

}
