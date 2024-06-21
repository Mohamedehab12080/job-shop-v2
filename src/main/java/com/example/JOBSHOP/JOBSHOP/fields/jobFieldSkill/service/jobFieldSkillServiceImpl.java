package com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.jobFieldSkill;

@Service
public class jobFieldSkillServiceImpl implements jobFieldSkillServiceInterface {

	@Autowired
	private jobFieldSkillRepository jobFieldSkillRepository;

	@Override
	public void deleteJobSkill(Long id) {
		jobFieldSkillRepository.deleteById(id);

	}

	@Override
	public jobFieldSkill findById(Long id) {
		return jobFieldSkillRepository.findById(id).get();
	}

	@Override
	public jobFieldSkill findBySkillIdAndJobId(Long skillId, Long jobId) {
		return jobFieldSkillRepository.findBySkillIdAndJobModelId(skillId, jobId);
	}

	@Override
	public jobFieldSkill findBySkillNameAndJobName(String jobName, String skillName) {
		return jobFieldSkillRepository.findBySkillSkillNameAndJobModelName(skillName, jobName);
	}

	@Override
	public List<jobFieldSkill> findByJobId(Long Id) {
		return jobFieldSkillRepository.findByJobModelId(Id);
	}

	@Override
	public List<jobFieldSkill> findByJobName(String jobName) {
		return jobFieldSkillRepository.findByJobModelName(jobName);
	}

}
