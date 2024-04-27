package com.example.JOBSHOP.JOBSHOP.skills.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.skills.Skill;
public interface skillServiceInterface {

	
	List<Skill> findBySkillName(String skillName);
	Skill findByName(String skillName);
	int insert(Skill skill);
	Skill insertForJobSeekerOperation(Skill skill);
	void deleteById(Long id);
	Optional<Skill> findById(Long id);
	List<String>findAllDistinct();
	void updateSkill(Long id,Skill skill);
	void insertAll(List<Skill> skillsToInsert);
	Long findIdByName(String skillName);
	
}
