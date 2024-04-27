package com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;

public class applicationQualificationMapper {

	public static applicationQualificationDTO mapApplicationQualificationToDTO(applicationQualification appQual)
	{
		applicationQualificationDTO dto=new applicationQualificationDTO();
		dto.setId(appQual.getId());
		dto.setApplication(appQual.getApplication());
		dto.setJobSeekerQualification(appQual.getJobSeekerQualification());
		dto.setJobSeekerQualificationId(appQual.getJobSeekerQualification().getId());
		dto.setApplicationId(appQual.getApplication().getId());
		dto.setQualificationName(appQual.getJobSeekerQualification().getQualification().getQualificationName());
		dto.setQualificationDegree(appQual.getJobSeekerQualification().getQualificationDegree());
		return dto;
	}
	
	public static applicationQualification mapDTOToApplicationQualification(applicationQualificationDTO dto)
	{
		applicationQualification app=new applicationQualification();
		app.setId(dto.getId());
		if(dto.getApplication()!=null)
		{
			app.setApplication(dto.getApplication());
		}else
		{
			Application appli=new Application();
			appli.setId(dto.getApplicationId());
			app.setApplication(appli);
		}
		if(dto.getJobSeekerQualification()!=null)
		{
			app.setJobSeekerQualification(dto.getJobSeekerQualification());
		}else 
		{
			jobSeekerQualification jobseekerQ=new jobSeekerQualification();
			jobseekerQ.setId(dto.getJobSeekerQualificationId());
			app.setJobSeekerQualification(jobseekerQ);
		}
		return app;
	}
}
