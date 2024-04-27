package com.example.JOBSHOP.JOBSHOP.skills.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.skills.Skill;

@Repository
public interface skillRepository extends JpaRepository<Skill,Long>{
	
	Skill findBySkillName(String skillName);
	
	@Query(value = "Select s from Skill s where s.skillName like %:skillName%")
	List<Skill> findBySkillNameList(@Param("skillName") String skillName);
	
	@Query(value = "Select DISTINCT s.skillName from Skill s")
	List<String> findAllDistinctSkills();

	@Query(value="select s.id from Skill s where s.skillName=:skillName")
	Long findIdByName(@Param("skillName") String skillName);
	
}
