package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.DTO;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.jobQualificationModel;

public class jobQualificationMapper {

	
	public static jobQualificationDTO mapJobQualificationToDTO(jobQualificationModel job)
	{
		jobQualificationDTO dto=new jobQualificationDTO();
		jobModel jobModel=job.getJobModel();
		Qualification qual=job.getQualification();
			if(jobModel!=null)
			{
				dto.setJobId(jobModel.getId());
				dto.setJobName(jobModel.getName());
			}
			if(qual!=null)
			{
				dto.setQualificationId(qual.getId());
				dto.setQualificationName(qual.getQualificationName());
			}
			dto.setId(job.getId());
		return dto;
	}
}
