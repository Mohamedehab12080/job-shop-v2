package com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.service;

import com.example.JOBSHOP.JOBSHOP.fields.jobFieldSkill.jobFieldSkill;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

public class jobFieldSkillDTOMapper {

	
	public static jobFieldSkillDTO mapJobFieldSkillToDTO(jobFieldSkill skill)
	{
		jobFieldSkillDTO dto=new jobFieldSkillDTO();
		dto.setJobFieldSkillId(skill.getId());
		dto.setJobId(skill.getJobModel().getId());
		dto.setJobName(skill.getJobModel().getName());
		dto.setSkillId(skill.getSkill().getId());
		dto.setSkillName(skill.getSkill().getSkillName());
		return dto;
	}
	
	public static jobFieldSkill mapDtoToJobFieldSkill(jobFieldSkillDTO dto)
	{
		jobFieldSkill skill=new jobFieldSkill();
		skill.setId(dto.getJobFieldSkillId());
		jobModel job=new jobModel();
		job.setId(dto.getJobId());
		job.setName(dto.getJobName());
		skill.setJobModel(job);
		Skill skillObj=new Skill();
		skillObj.setId(dto.getSkillId());
		skillObj.setSkillName(dto.getSkillName());
		skill.setSkill(skillObj);
		return skill;
	}
}
