package com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

public class jobSeekerSkillMapper {

	public static jobSeekerSkillDTO mapJobSeekerSkillToDTO(jobSeekerSkill jobSeekerSkill)
	{
		jobSeekerSkillDTO jobSeekerSkillDto=new jobSeekerSkillDTO();
		
		jobSeekerSkillDto.setCreatedBy(jobSeekerSkill.getCreatedBy());
		jobSeekerSkillDto.setCreatedDate(jobSeekerSkill.getCreatedDate());
		jobSeekerSkillDto.setLastModifiedBy(jobSeekerSkill.getLastModifiedBy());
		jobSeekerSkillDto.setLastModifiedDate(jobSeekerSkill.getLastModifiedDate());
		jobSeekerSkillDto.setId(jobSeekerSkill.getId());
//		jobSeekerSkillDto.setJobSeeker(jobSeekerSkill.getJobSeeker());
//		jobSeekerSkillDto.setSkill(jobSeekerSkill.getSkill());
		jobSeekerSkillDto.setJobSeekerId(jobSeekerSkill.getJobSeeker().getId());
		jobSeekerSkillDto.setSkillId(jobSeekerSkill.getSkill().getId());
		jobSeekerSkillDto.setSkillName(jobSeekerSkill.getSkill().getSkillName());
		jobSeekerSkillDto.setSkillDegree(jobSeekerSkill.getSkillDegree());
		
		return jobSeekerSkillDto;
	}
	
	public static jobSeekerSkill mapDtoToJobSeekerSkill(jobSeekerSkillDTO jobSeekerSkillDto)
	{
		jobSeekerSkill jobSeeker=new jobSeekerSkill();
		
		jobSeeker.setCreatedBy(jobSeekerSkillDto.getCreatedBy());
		jobSeeker.setCreatedDate(jobSeekerSkillDto.getCreatedDate());
		jobSeeker.setLastModifiedBy(jobSeekerSkillDto.getLastModifiedBy());
		jobSeeker.setLastModifiedDate(jobSeekerSkillDto.getLastModifiedDate());
		jobSeeker.setId(jobSeekerSkillDto.getId());
//		jobSeekerSkillDto.setJobSeeker(jobSeekerSkill.getJobSeeker());
//		jobSeekerSkillDto.setSkill(jobSeekerSkill.getSkill());
		jobSeeker.setSkillDegree(jobSeekerSkillDto.getSkillDegree());
		
		return jobSeeker;
	}
	
	public static jobSeekerSkill mapDtoToJobSeekerSkillForInsert(jobSeekerSkillDTO jobSeekerSkillDto)
	{
		jobSeekerSkill jobSeeker=new jobSeekerSkill();
		
		jobSeeker.setCreatedBy(jobSeekerSkillDto.getCreatedBy());
		jobSeeker.setCreatedDate(jobSeekerSkillDto.getCreatedDate());
		jobSeeker.setLastModifiedBy(jobSeekerSkillDto.getLastModifiedBy());
		jobSeeker.setLastModifiedDate(jobSeekerSkillDto.getLastModifiedDate());
		jobSeeker.setId(jobSeekerSkillDto.getId());
		if(jobSeekerSkillDto.getJobSeeker()!=null)
		{
			jobSeeker.setJobSeeker(jobSeekerSkillDto.getJobSeeker());
		}else 
		{
			jobSeeker jobSeekerObject=new jobSeeker();
			jobSeekerObject.setId(jobSeekerSkillDto.getJobSeekerId());
			jobSeeker.setJobSeeker(jobSeekerObject);

		}
		if(jobSeekerSkillDto.getSkill()!=null)
		{
			jobSeeker.setSkill(jobSeekerSkillDto.getSkill());
		
		}else 
		{
			Skill skillObject=new Skill();
			skillObject.setId(jobSeekerSkillDto.getSkillId());
			jobSeeker.setSkill(skillObject);
		}
		
		jobSeeker.setSkillDegree(jobSeekerSkillDto.getSkillDegree());
		return jobSeeker;
	}
}
