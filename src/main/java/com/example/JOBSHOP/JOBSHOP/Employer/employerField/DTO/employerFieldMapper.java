package com.example.JOBSHOP.JOBSHOP.Employer.employerField.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerFieldShowDTO.employerFieldShowDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;

public class employerFieldMapper {

//	public static employerFieldDTO mapEmployerFieldToDTO(employerField empf)
//	{
//		employerFieldDTO dto=new employerFieldDTO();
//		List<String> skills=new ArrayList<String>();
////		Employer employer=empf.getEmployer();
//		dto.setEmployerId(empf.getEmployer().getId());
//		dto.setEmployerUserName(empf.getEmployer().getUserName());
//		dto.setCreatedBy(empf.getCreatedBy());
//		dto.setCreatedDate(empf.getCreatedDate());
//		dto.setLastModifiedBy(empf.getLastModifiedBy());
//		dto.setLastModifiedDate(empf.getLastModifiedDate());
////		dto.setCompanyField(empf.getCompanyField());
//		dto.setStatuseCode(empf.getStatusCode());
//		dto.setFieldName(empf.getCompanyField().getFieldName());
//		dto.setCompanyFieldId(empf.getCompanyField().getId());
////		dto.setEmployer(employer);
//		for(companyFieldSkill skill:empf.getCompanyField().getCompanyFieldSkills())
//		{
//			skills.add(skill.getCompanyFieldSkill().getSkillName());  // it should be map <id , skillName>
//		}
//		dto.setSkills(skills);
//		dto.setId(empf.getId());
//		return dto;
//	}
//	
	public static employerFieldShowDTO mapEmployerFieldToDTO(employerField empf)
	{
		employerFieldShowDTO dto=new employerFieldShowDTO();
		List<String> skills=new ArrayList<String>();
		List<String> qualifications=new ArrayList<String>();
		for(companyFieldSkill skill:empf.getCompanyField().getCompanyFieldSkills())
		{
			skills.add(skill.getCompanyFieldSkill().getSkillName());  // it should be map <id , skillName>
		}
		
		for(companyFieldQualification qual:empf.getCompanyField().getCompanyFieldQualifications())
		{
			qualifications.add(qual.getQualification().getQualificationName());  // it should be map <id , skillName>
		}
		dto.setFieldName(empf.getCompanyField().getFieldName());
		dto.setId(empf.getId());
		dto.setSkills(skills);
		dto.setQualifications(qualifications);
		return dto;
	}
	
	public static employerField mapDTOToEmployerField(employerFieldDTO dto)
	{
		employerField empField=new employerField();
		
		if(dto.getCompanyField()!=null)
		{
			empField.setCompanyField(dto.getCompanyField());
		}else 
		{
			companyField companyField=new companyField() ;
			companyField.setId(dto.getCompanyFieldId());
			empField.setCompanyField(companyField);
		}
		empField.setCreatedDate(dto.getCreatedDate());
		empField.setLastModifiedDate(dto.getLastModifiedDate());
		empField.setCreatedBy(dto.getCreatedBy());
		empField.setLastModifiedBy(dto.getLastModifiedBy());
		empField.setEmployer(dto.getEmployer());
		empField.setId(dto.getId());
		empField.setStatusCode(dto.getStatuseCode());
		return empField;
	}
	
	public static employerField mapDtoToInsertToEmployerField(employerFieldForInsert empfInsert)
	{
		employerField empf=new employerField();
			
		companyField compf=new companyField();
		compf.setId(empfInsert.getId());
		empf.setCompanyField(compf);
		Employer emp=new Employer();
		emp.setId(empfInsert.getEmployerId());
		empf.setEmployer(emp);
		
		return empf;
	}
}
