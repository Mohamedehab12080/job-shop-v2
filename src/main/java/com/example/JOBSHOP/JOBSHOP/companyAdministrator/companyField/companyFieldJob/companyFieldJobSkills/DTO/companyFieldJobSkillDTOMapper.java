package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.DTO;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.companyFieldJobSkill;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

public class companyFieldJobSkillDTOMapper {

	
	public static companyFieldJobSkillDTO mapCompanyFieldJobSkillToDTO(companyFieldJobSkill compSkill)
	{
		if(compSkill!=null)
		{
			companyFieldJobSkillDTO dto=new companyFieldJobSkillDTO();
			companyFieldJob compJob=compSkill.getCompanyFieldJob();
			if(compJob!=null)
			{
				dto.setCompanyFieldJobId(compJob.getId());
				if(compJob.getJobModel()!=null)
				{
					dto.setJobId(compJob.getJobModel().getId());
					dto.setJobName(compJob.getJobModel().getName());
				}
			}
			Skill skill=compSkill.getCompanyFieldJobSkill();
			if(skill!=null)
			{
				dto.setSkillId(skill.getId());
				dto.setSkillName(skill.getSkillName());
			}
			dto.setId(compSkill.getId());
			return dto;
		}else 
		{
			return null;
		}
	}
	
	public static companyFieldJobSkill mapDTOToCompanyFieldJobSkill(companyFieldJobSkillDTO dto)
	{
		companyFieldJobSkill compSkill=new companyFieldJobSkill();
		compSkill.setId(dto.getId());
		if(dto.getCompanyFieldJobId()!=null)
		{
			companyFieldJob compJob=new companyFieldJob();
			compJob.setId(dto.getCompanyFieldJobId());
			compSkill.setCompanyFieldJob(compJob);
		}
		if(dto.getSkillId()!=null)
		{
			Skill skill =new Skill();
			skill.setId(dto.getSkillId());
			compSkill.setCompanyFieldJobSkill(skill);
		}
		return compSkill;
	}
}
