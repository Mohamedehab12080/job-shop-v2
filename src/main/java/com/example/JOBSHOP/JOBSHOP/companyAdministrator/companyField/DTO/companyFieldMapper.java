package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;

public class companyFieldMapper {

	public static companyFieldDTO mapCompanyFieldToDTO(companyField companyField)
	{
		List<String> skills=new ArrayList<String>();
		List<String> qualifications=new ArrayList<String>();
		Map<Long,String> skillsMap=new HashMap<Long, String>();
		companyFieldDTO dto=new companyFieldDTO();
		dto.setId(companyField.getId());
		dto.setCreatedBy(companyField.getCreatedBy());
		dto.setCreatedDate(companyField.getCreatedDate());
		dto.setLastModifiedBy(companyField.getLastModifiedBy());
		dto.setLastModifiedDate(companyField.getLastModifiedDate());
		dto.setStatuseCode(companyField.getStatusCode());
		dto.setCompanyAdministratorId(companyField.getCompanyAdmin().getId());
		dto.setCompanyName(companyField.getCompanyAdmin().getCompanyName());
		dto.setFieldName(companyField.getFieldName());
//		dto.setRequiredQualifications(companyField.getRequiredQualifications());
		for(companyFieldSkill Skill:companyField.getCompanyFieldSkills())
		{
			skillsMap.put(Skill.getId(),Skill.getCompanyFieldSkill().getSkillName());
			dto.setSkillsMap(skillsMap);
			skills.add(Skill.getCompanyFieldSkill().getSkillName());
		}
		for(companyFieldQualification qual:companyField.getCompanyFieldQualifications())
		{
			
			qualifications.add(qual.getQualification().getQualificationName());
		}
		dto.setQualifications(qualifications);
		dto.setSkills(skills);
		
		  // Need for serializer
//		dto.setCompanyFieldQualifications(companyField.getCompanyFieldQualifications());
//		dto.setCompanyFieldSkills(companyField.getCompanyFieldSkills());
//		dto.setCompanyFieldSkills(companyField.getCompanyFieldSkills());
		return dto;
	}
	
	public static companyField mapDTOToCompanyField(companyFieldDTO companyFieldDto)
	{
		companyField companyField=new companyField();
		companyField.setId(companyFieldDto.getId());
		companyField.setCreatedBy(companyFieldDto.getCreatedBy());
		companyField.setCreatedDate(companyFieldDto.getCreatedDate());
		companyField.setLastModifiedBy(companyFieldDto.getLastModifiedBy());
		companyField.setLastModifiedDate(companyFieldDto.getLastModifiedDate());
		companyField.setStatusCode(companyFieldDto.getStatuseCode());
			companyAdministrator compAdm=new companyAdministrator();
			compAdm.setId(companyFieldDto.getCompanyAdministratorId());
			compAdm.setCompanyName(companyFieldDto.getCompanyName());
		companyField.setCompanyAdministrator(compAdm);
		companyField.setFieldName(companyFieldDto.getFieldName());
//		companyField.setRequiredQualifications(companyFieldDto.getRequiredQualifications());
		companyField.setCompanyFieldSkills(companyFieldDto.getCompanyFieldSkills());
		companyField.setCompanyFieldQualifications(companyFieldDto.getCompanyFieldQualifications());
		return companyField;
	}
}
