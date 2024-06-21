package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.jobSkillModel;

public interface jobSkillModelServiceInterface {

	List<jobSkillModel> findByJobModelId(Long JobModelId);
	jobSkillModel findByJobModelIdAndSkillId(Long jobModelId,Long SkillId);
	jobSkillModel insert(jobSkillModel jobSkillModel);
	String delete(Long id);
	List<jobSkillModel> insertAll(List<jobSkillModel> jobSkillModels);
}
