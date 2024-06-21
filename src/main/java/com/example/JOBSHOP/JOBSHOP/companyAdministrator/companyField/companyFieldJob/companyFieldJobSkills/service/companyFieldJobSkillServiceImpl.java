package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.companyFieldJobSkill;

public class companyFieldJobSkillServiceImpl implements companyFieldJobSkillServiceInterface{

	@Autowired
	private companyFieldJobSkillRepository companyFieldJobSkillRepository;
	@Override
	public List<companyFieldJobSkill> findBySkillId(Long id) {
		return companyFieldJobSkillRepository.findBycompanyFieldJobSkillId(id);
	}

	@Override
	public companyFieldJobSkill insert(companyFieldJobSkill companyFieldJobSkill) {
		return companyFieldJobSkillRepository.save(companyFieldJobSkill);
	}

	@Override
	public String delete(Long id) {
		if(findById(id)!=null)
		{
			companyFieldJobSkillRepository.deleteById(id);
			return "Deleted";
		}else 
		{
			return "Not found";
		}
	}

	@Override
	public List<companyFieldJobSkill> findByCompanyFieldJobId(Long companyFieldJobId) {
		return companyFieldJobSkillRepository.findByCompanyFieldJobId(companyFieldJobId);
	}

	@Override
	public companyFieldJobSkill findById(Long id) {
		return companyFieldJobSkillRepository.findById(id).get();
	}

	@Override
	public void insertAll(List<companyFieldJobSkill> companyFieldJobSkills) {
	
		companyFieldJobSkillRepository.saveAll(companyFieldJobSkills);
	}

}
