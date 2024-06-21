package com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.jobFieldSkill;

@Repository
public interface jobFieldSkillRepository extends JpaRepository<jobFieldSkill, Long>{

	
	@Query(name="SELECT s FROM jobFieldSkill s where s.Skill.id=:skillId and s.jobModel.id=:jobModelId")
	jobFieldSkill findBySkillIdAndJobModelId(Long skillId,Long jobModelId);

	@Query(name="SELECT s FROM jobFieldSkill s where s.Skill.skillName=:skillName and s.jobModel.name=:jobName")
	jobFieldSkill findBySkillSkillNameAndJobModelName(String skillName,String jobName);
	
	@Query(name="SELECT s FROM jobFieldSkill s where s.jobModel.id=:jobId")
	List<jobFieldSkill> findByJobModelId(Long jobId);
	
	@Query(name="SELECT s FROM jobFieldSkill s where s.jobModel.name=:jobName")
	List<jobFieldSkill> findByJobModelName(String jobName);
}
