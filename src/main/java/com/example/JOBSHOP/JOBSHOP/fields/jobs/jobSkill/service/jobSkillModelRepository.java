package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.jobSkillModel;

@Repository
public interface jobSkillModelRepository extends JpaRepository<jobSkillModel, Long>{

	List<jobSkillModel> findByJobModelId(Long jobModelId);

	jobSkillModel findByJobModelIdAndSkillId(Long jobModelId, Long skillId);
	
}
