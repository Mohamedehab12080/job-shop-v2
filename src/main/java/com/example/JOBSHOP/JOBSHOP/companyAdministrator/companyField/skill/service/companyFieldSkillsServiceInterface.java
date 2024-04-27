package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;

public interface companyFieldSkillsServiceInterface {

		Optional<companyFieldSkill> findBySkillId(Long id);
		String insert(companyFieldSkill companyFieldSkill);
		String update(Long id,companyFieldSkill companyFieldSkill);
		String delete(Long id);
		List<companyFieldSkill> findByCompanyFieldId(Long companyFieldId);
		Optional<companyFieldSkill> findById(Long id);
		void insertAll(List<companyFieldSkill> companyFieldSkills);
}
