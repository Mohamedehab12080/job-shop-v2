package com.example.JOBSHOP.JOBSHOP.Application.skill.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;

@Repository
public interface
applicationSkillRepository extends JpaRepository<applicationSkill,Long>{

	List<applicationSkill> findByApplicationId	(Long appId);

}
