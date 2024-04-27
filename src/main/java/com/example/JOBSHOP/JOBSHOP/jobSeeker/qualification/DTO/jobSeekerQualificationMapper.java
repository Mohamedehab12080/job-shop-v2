package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO;

import com.example.JOBSHOP.JOBSHOP.degrees.qualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;

public class jobSeekerQualificationMapper {
	public static jobSeekerQualificationDTO mapJobSeekerQualificationToDTO(jobSeekerQualification jobSeekerQualification)
	{
		jobSeekerQualificationDTO jobSeekerQualificationDto=new jobSeekerQualificationDTO();
		
		jobSeekerQualificationDto.setCreatedBy(jobSeekerQualification.getCreatedBy());
		jobSeekerQualificationDto.setCreatedDate(jobSeekerQualification.getCreatedDate());
		jobSeekerQualificationDto.setLastModifiedBy(jobSeekerQualification.getLastModifiedBy());
		jobSeekerQualificationDto.setLastModifiedDate(jobSeekerQualification.getLastModifiedDate());
		jobSeekerQualificationDto.setId(jobSeekerQualification.getId());
//		jobSeekerSkillDto.setJobSeeker(jobSeekerQualification.getJobSeeker());
//		jobSeekerSkillDto.setSkill(jobSeekerQualification.getSkill());
		jobSeekerQualificationDto.setJobSeekerId(jobSeekerQualification.getJobSeeker().getId());
		jobSeekerQualificationDto.setQualificationId(jobSeekerQualification.getQualification().getId());
		jobSeekerQualificationDto.setQualificationName(jobSeekerQualification.getQualification().getQualificationName());
		jobSeekerQualificationDto.setQualificationDegree(jobSeekerQualification.getQualificationDegree());
		
		return jobSeekerQualificationDto;
	}
	
	public static jobSeekerQualification mapDtoToJobSeekerQualification(jobSeekerQualificationDTO jobSeekerQualificationDto)
	{
		jobSeekerQualification jobSeekerQualification=new jobSeekerQualification();
		
		jobSeekerQualification.setCreatedBy(jobSeekerQualificationDto.getCreatedBy());
		jobSeekerQualification.setCreatedDate(jobSeekerQualificationDto.getCreatedDate());
		jobSeekerQualification.setLastModifiedBy(jobSeekerQualificationDto.getLastModifiedBy());
		jobSeekerQualification.setLastModifiedDate(jobSeekerQualificationDto.getLastModifiedDate());
		jobSeekerQualification.setId(jobSeekerQualificationDto.getId());
//		jobSeekerSkillDto.setJobSeeker(jobSeekerSkill.getJobSeeker());
//		jobSeekerSkillDto.setSkill(jobSeekerSkill.getSkill());
		jobSeekerQualification.setQualificationDegree(jobSeekerQualificationDto.getQualificationDegree());
		
		return jobSeekerQualification;
	}
	

	public static jobSeekerQualification mapDtoToJobSeekerQualificationForInsert(jobSeekerQualificationDTO jobSeekerQualificationDto)
	{
		jobSeekerQualification jobSeekerQualification=new jobSeekerQualification();
		
		jobSeekerQualification.setCreatedBy(jobSeekerQualificationDto.getCreatedBy());
		jobSeekerQualification.setCreatedDate(jobSeekerQualificationDto.getCreatedDate());
		jobSeekerQualification.setLastModifiedBy(jobSeekerQualificationDto.getLastModifiedBy());
		jobSeekerQualification.setLastModifiedDate(jobSeekerQualificationDto.getLastModifiedDate());
		jobSeekerQualification.setId(jobSeekerQualificationDto.getId());
		if(jobSeekerQualificationDto.getJobSeeker()!=null)
		{
			jobSeekerQualification.setJobSeeker(jobSeekerQualificationDto.getJobSeeker());
		}else 
		{
			jobSeeker jobSeekerObject=new jobSeeker();
			jobSeekerObject.setId(jobSeekerQualificationDto.getJobSeekerId());
			jobSeekerQualification.setJobSeeker(jobSeekerObject);

		}
		if(jobSeekerQualificationDto.getQualification()!=null)
		{
			jobSeekerQualification.setQualification(jobSeekerQualificationDto.getQualification());
		
		}else 
		{
			qualification qualificationObject=new qualification();
			qualificationObject.setId(jobSeekerQualificationDto.getQualificationId());
			jobSeekerQualification.setQualification(qualificationObject);
		}
		
		jobSeekerQualification.setQualificationDegree(jobSeekerQualificationDto.getQualificationDegree());
		return jobSeekerQualification;
	}

}
