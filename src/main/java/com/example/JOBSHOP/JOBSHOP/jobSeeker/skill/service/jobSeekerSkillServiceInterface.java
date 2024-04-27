package com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;

public interface jobSeekerSkillServiceInterface {

	jobSeekerSkill getReferenceById(Long id);
	List<jobSeekerSkill> findByJobSeekerId(Long id);
	jobSeekerSkill insert(jobSeekerSkill entity);
	Optional<jobSeekerSkill> findById(Long id);
	List<jobSeekerSkill> insertAll(List<jobSeekerSkill> skillsList);
	String deleteById(Long id);
	Optional<jobSeekerSkill> findByJobSeekerIdAndSkillId(Long jobSeekerId,Long skillId);
	
}
