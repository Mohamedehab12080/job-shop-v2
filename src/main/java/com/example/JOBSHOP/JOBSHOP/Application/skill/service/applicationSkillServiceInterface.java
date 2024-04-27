package com.example.JOBSHOP.JOBSHOP.Application.skill.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;

public interface applicationSkillServiceInterface {

	List<applicationSkill> insertAll(List<applicationSkill> appSkillList);
	applicationSkill insert(applicationSkill appSkill);
	applicationSkill update(Long id,applicationSkill appSkill);
	String deleteById(Long id);
	List<applicationSkill> findByApplicationId(Long appId);
	applicationSkill findById(Long id);
}
