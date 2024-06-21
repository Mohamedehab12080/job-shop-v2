package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.jobQualificationModel;

@Service
public class jobQualificationServiceImpl implements jobQualificationServiceInterface{

	
	@Autowired
	private jobQualificationRepository jobQualificationRepository;
	
	@Override
	public List<jobQualificationModel> findByJobModelId(Long JobModelId) {
		return jobQualificationRepository.findByJobModelId(JobModelId);
	}

	@Override
	public jobQualificationModel findByJobModelIdAndQualificationId(Long jobModelId, Long qualificationId) {
		return jobQualificationRepository.findByJobModelIdAndQualificationId(jobModelId, qualificationId);
	}

	@Override
	public jobQualificationModel insert(jobQualificationModel jobQualificationModel) {
		return jobQualificationRepository.save(jobQualificationModel);
	}

	@Override
	public String delete(Long id) {
		if(jobQualificationRepository.findById(id).isPresent())
		{
			jobQualificationRepository.deleteById(id);
			return "Deleted";
		}else
		{
			return "Not found";
		}
	}

	@Override
	public List<jobQualificationModel> insertAll(List<jobQualificationModel> jobSkillModels) {
		return jobQualificationRepository.saveAll(jobSkillModels);
	}

}
