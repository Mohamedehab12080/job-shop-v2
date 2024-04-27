package com.example.JOBSHOP.JOBSHOP.Application.skill.DTO;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;

public class applicationSkillMapper {

	public static applicationSkillDTO mapApplicationSkillToDTO(applicationSkill appSkill)
	{
		applicationSkillDTO dto=new applicationSkillDTO();
		
		dto.setApplication(appSkill.getApplication());
		dto.setApplicationId(appSkill.getApplication().getId());
		dto.setJobSeekerSkill(appSkill.getJobSeekerSkill());
		dto.setJobSeekerSkillId(appSkill.getJobSeekerSkill().getId());
		dto.setId(appSkill.getId());
		dto.setSkillName(appSkill.getJobSeekerSkill().getSkill().getSkillName());
		dto.setSkillDegree(appSkill.getJobSeekerSkill().getSkillDegree());
		return dto;
	}
	
	public static applicationSkill mapDTOToApplicationSkill(applicationSkillDTO dto)
	{
		applicationSkill app=new applicationSkill();
		
		if(dto.getApplication()!=null)
		{
			app.setApplication(dto.getApplication());
		}else 
		{
			Application obj=new Application();
			obj.setId(dto.getApplicationId());
			app.setApplication(obj);
		}
		
		if(dto.getJobSeekerSkill()!=null)
		{
		app.setJobSeekerSkill(dto.getJobSeekerSkill());
		}else 
		{
			jobSeekerSkill obj=new jobSeekerSkill();
			obj.setId(dto.getJobSeekerSkillId());
			app.setJobSeekerSkill(obj);
		}
		app.setId(dto.getId());

		return app;
		 
	}
}
