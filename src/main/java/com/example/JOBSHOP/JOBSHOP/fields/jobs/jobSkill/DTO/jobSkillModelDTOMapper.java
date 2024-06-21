package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.jobSkillModel;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

public class jobSkillModelDTOMapper {

	public static jobSkillModelDTO mapJobSkillModelToDTO(jobSkillModel job)
	{
		jobSkillModelDTO dto=new jobSkillModelDTO();
		jobModel jobModel=job.getJobModel();
		Skill skill=job.getSkill();
			if(jobModel!=null)
			{
				dto.setJobId(jobModel.getId());
				dto.setJobName(jobModel.getName());
			}
			if(skill!=null)
			{
				dto.setSkillId(skill.getId());
				dto.setSkillName(skill.getSkillName());
			}
			dto.setId(job.getId());
		return dto;
	}
	
	
}
