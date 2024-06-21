package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.jobQualificationModel;


public interface jobQualificationServiceInterface {
	
	List<jobQualificationModel> findByJobModelId(Long JobModelId);
	jobQualificationModel findByJobModelIdAndQualificationId(Long jobModelId,Long qualificationId);
	jobQualificationModel insert(jobQualificationModel jobQualificationModel);
	String delete(Long id);
	List<jobQualificationModel> insertAll(List<jobQualificationModel> jobSkillModels);
}
