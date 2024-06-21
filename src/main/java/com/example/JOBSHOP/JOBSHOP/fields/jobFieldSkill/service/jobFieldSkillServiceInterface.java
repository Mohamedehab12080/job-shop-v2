package com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.jobFieldSkill;

public interface jobFieldSkillServiceInterface {
	
	public void deleteJobSkill(Long id);
	public jobFieldSkill findById(Long id);
	public jobFieldSkill findBySkillIdAndJobId(Long skillId,Long jobId);
	public jobFieldSkill findBySkillNameAndJobName(String jobName,String skillName);
	public List<jobFieldSkill> findByJobId(Long Id);
	public List<jobFieldSkill> findByJobName(String jobName);
	
}
