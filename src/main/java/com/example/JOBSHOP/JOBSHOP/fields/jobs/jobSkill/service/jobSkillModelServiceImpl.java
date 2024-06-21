package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.jobSkillModel;

@Service
public class jobSkillModelServiceImpl implements jobSkillModelServiceInterface{

	@Autowired
	private jobSkillModelRepository jobSkillModelRepository;
	
	@Override
	public List<jobSkillModel> findByJobModelId(Long JobModelId) {
		return jobSkillModelRepository.findByJobModelId(JobModelId);
	}

	@Override
	public jobSkillModel findByJobModelIdAndSkillId(Long jobModelId, Long SkillId) {
		return jobSkillModelRepository.findByJobModelIdAndSkillId(jobModelId,SkillId);
	}

	@Override
	public jobSkillModel insert(jobSkillModel jobSkillModel) {
		return jobSkillModelRepository.save(jobSkillModel);
	}

	@Override
	public List<jobSkillModel> insertAll(List<jobSkillModel> jobSkillModels) {
		return jobSkillModelRepository.saveAll(jobSkillModels);
	}

	@Override
	public String delete(Long id) {
		if(jobSkillModelRepository.findById(id).isPresent())
		{
			jobSkillModelRepository.deleteById(id);
			return "Deleted";
		}else
		{
			return "Not Found";	
		}
	}

}
