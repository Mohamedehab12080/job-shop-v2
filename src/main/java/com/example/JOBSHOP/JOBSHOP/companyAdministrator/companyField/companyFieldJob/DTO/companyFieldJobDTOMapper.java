package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

public class companyFieldJobDTOMapper {

	public static companyFieldJobDTO mapcompanyFieldJobToDTO(companyFieldJob comp)
	{
		companyFieldJobDTO dto=new companyFieldJobDTO();
		companyField compField=comp.getCompanyField();
		jobModel jobModel=comp.getJobModel();
		dto.setId(comp.getId());
		if(compField!=null)
		{
			dto.setCompanyFieldId(compField.getId());
			if(compField.getField()!=null)
			{
				dto.setFieldId(compField.getField().getId());
				dto.setFieldName(compField.getField().getFieldName());
			}
			
		}
		if(jobModel!=null)
		{
			dto.setJobId(jobModel.getId());
			dto.setJobName(jobModel.getName());
		}
		
		dto.setCompanyFieldJobId(comp.getId());
		
		return dto;
	}
	
	public static companyFieldJob mapCompanyFieldJobDTOTocompanyFieldJob(companyFieldJobDTO dto)
	{
		companyFieldJob comp=new companyFieldJob();
		comp.setId(dto.getCompanyFieldJobId());
		if(dto.getCompanyFieldId()!=null)
		{
			companyField compField=new companyField();
			compField.setId(dto.getCompanyFieldId());
			comp.setCompanyField(compField);
		}
		
		if(dto.getJobId()!=null)
		{
			jobModel jobObj=new jobModel();
			jobObj.setId(dto.getJobId());
			comp.setJobModel(jobObj);
		}
		
		return comp; 
	}
}
