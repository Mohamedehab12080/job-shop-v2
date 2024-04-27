package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;

@Repository
public interface companyFieldSkillsRepository extends JpaRepository<companyFieldSkill,Long>{

	List<companyFieldSkill> findByCompanyFieldId(Long id);
	
	Optional<companyFieldSkill> findByCompanyFieldSkillId(Long id); 
}
