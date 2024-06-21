package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.companyFieldJobSkill;

public interface companyFieldJobSkillServiceInterface {

	
	List<companyFieldJobSkill> findBySkillId(Long id);
	
	companyFieldJobSkill insert(companyFieldJobSkill companyFieldJobSkill);
	String delete(Long id);
	List<companyFieldJobSkill> findByCompanyFieldJobId(Long companyFieldJobId);
	companyFieldJobSkill findById(Long id);
	void insertAll(List<companyFieldJobSkill>companyFieldJobSkills);
	
}
