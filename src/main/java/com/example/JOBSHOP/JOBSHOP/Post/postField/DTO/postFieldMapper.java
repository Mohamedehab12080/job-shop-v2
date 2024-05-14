package com.example.JOBSHOP.JOBSHOP.Post.postField.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.Qualification.companyFieldQualification;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.skill.companyFieldSkill;

public class postFieldMapper {

	public static postFieldDTO mapPostFieldToDTO(postField field)
	{
		postFieldDTO dto =new postFieldDTO();
		dto.setCreatedBy(field.getCreatedBy());
		dto.setCreatedDate(field.getCreatedDate());
		dto.setLastModifiedBy(field.getLastModifiedBy());
		dto.setLastModifiedDate(field.getLastModifiedDate());
		dto.setStatuseCode(field.getStatusCode());
		dto.setId(field.getId());
		dto.setPost(field.getPost());
		dto.setEmployerField(field.getEmployerField());
		dto.setFieldName(field.getEmployerField().getCompanyField().getField().getFieldName());
		dto.setSkills(field.getSkills());
		dto.setQualifications(field.getQualifications());
		return dto;
	}
	
	public static postFieldDTO mapPostFieldDtoToPostFieldForPost(postField field)
	{
		postFieldDTO dto =new postFieldDTO();
//		Set<String> postskillsSet=new HashSet<String>(field.getSkills());
//
//
//		
//		Set<String> postQualSet=new HashSet<String>(field.getQualifications());
//		
//		
//		for(companyFieldSkill skill:field
//									.getEmployerField()
//									.getCompanyField()
//									.getCompanyFieldSkills())
//		{
//			postskillsSet.add(skill.getCompanyFieldSkill().getSkillName());
//		}
//		
//		for(companyFieldQualification qual:field.getEmployerField().getCompanyField().getCompanyFieldQualifications())
//		{
//			postQualSet.add(qual.getQualification().getQualificationName());
//		}
//		
//		List <String> postSkillsList=new ArrayList<String>(postskillsSet);
//		List <String> postQualList=new ArrayList<String>(postQualSet);
		
		dto.setId(field.getId());
		dto.setEmployerField(field.getEmployerField());
		dto.setFieldName(field.getEmployerField().getCompanyField().getField().getFieldName());
		dto.setSkills(field.getSkills());
		dto.setQualifications(field.getQualifications());
		dto.setPost(field.getPost());
		return dto;
	}
	public static postField mapDTOToPostField(postFieldDTO dto)
	{
		try {
			postField postF=new postField();
			postF.setCreatedBy(dto.getCreatedBy());
			postF.setCreatedDate(dto.getCreatedDate());
			postF.setLastModifiedBy(dto.getLastModifiedBy());
			postF.setLastModifiedDate(dto.getLastModifiedDate());
			postF.setStatusCode(dto.getStatuseCode());
			postF.setId(dto.getId());
			postF.setPost(dto.getPost());
			postF.setEmployerField(dto.getEmployerField());
			postF.setSkills(dto.getSkills());
			postF.setQualifications(dto.getQualifications());
			return postF;
		} catch (Exception e) {
			System.out.println("Exception from mapDTOToPostField : "+e);
			return null;
		}
	} 
	
}
