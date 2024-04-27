package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.DTO;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.degrees.qualification;

public class companyFieldQualificationMapper {

	
	public static companyFieldQualification mapDTOToCompanyFieldQaulification(companyFieldQualificationDTO dto)
	{
		companyFieldQualification comQual=new companyFieldQualification();
		if(dto.getCompanyField()!=null)
		{
			comQual.setCompanyField(dto.getCompanyField());
		}else 
		{
			companyField obj=new companyField();
			obj.setId(dto.getCompanyFieldId());
		}
		
		if(dto.getQualification()!=null)
		{
			comQual.setQualification(dto.getQualification());
		}else 
		{
			qualification obj=new qualification();
			obj.setId(dto.getQualificationId());
		}
		
		comQual.setCreatedBy(dto.getCreatedBy());
		comQual.setCreatedDate(dto.getCreatedDate());
		comQual.setLastModifiedBy(dto.getLastModifiedBy());
		comQual.setLastModifiedDate(dto.getLastModifiedDate());
		comQual.setStatusCode(dto.getStatuseCode());
		comQual.setId(dto.getId());
		return comQual;
	}

	public static companyFieldQualificationDTO mapCompanyFieldQualToDto(companyFieldQualification com)
	{
		companyFieldQualificationDTO dto=new companyFieldQualificationDTO();
		
		dto.setCompanyField(com.getCompanyField());
		dto.setCompanyFieldId(com.getCompanyField().getId());
		dto.setCompanyFieldName(com.getCompanyField().getFieldName());
		dto.setQualification(com.getQualification());
		dto.setQualificationId(com.getQualification().getId());
		dto.setQualificationName(com.getQualification().getQualificationName());
		dto.setCreatedBy(com.getCreatedBy());
		dto.setCreatedDate(com.getCreatedDate());
		dto.setLastModifiedBy(com.getLastModifiedBy());
		dto.setLastModifiedDate(com.getLastModifiedDate());
		dto.setStatuseCode(com.getStatusCode());
		dto.setId(com.getId());
		
		return dto;
	}

}
