package com.example.JOBSHOP.JOBSHOP.Post.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldDTO;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;

public class postMapper {

	
	public static Post mapDTOToPost(postDTO dto)
	{
		Post post=new Post();
		post.setApplications(dto.getApplications());
//		post.setPostFields(dto.getPostFields());
		post.setCreatedBy(dto.getCreatedBy());
		post.setCreatedDate(dto.getCreatedDate());
		post.setLastModifiedBy(dto.getLastModifiedBy());
		post.setLastModifiedDate(dto.getLastModifiedDate());
		post.setTitle(dto.getTitle());
		companyProfile compF=new companyProfile();
		compF.setId(dto.getProfileId());
		post.setCompanyProfile(compF);
		post.setDescription(dto.getDescription());
		post.setJobRequirments(dto.getJobRequirments());
		post.setLocation(dto.getLocation());
		post.setEmploymentType(dto.getEmploymentType());
		Employer emp=new Employer();
		emp.setId(dto.getEmployerId());
		post.setEmployer(emp);
		post.setApplications(dto.getApplications());
		post.setFieldCount(dto.getFieldCount());
		post.setApplicationCount(dto.getApplicationCount());
		
		return post;
	}

	public static Post mapDTOToPostForInsert(postDTO dto)
	{
		Post post=new Post();
//		post.setApplications(dto.getApplications());
		post.setCreatedBy(dto.getCreatedBy());
		post.setCreatedDate(dto.getCreatedDate());
		post.setLastModifiedBy(dto.getLastModifiedBy());
		post.setLastModifiedDate(dto.getLastModifiedDate());
		post.setTitle(dto.getTitle());
		companyProfile compF=new companyProfile();
		compF.setId(dto.getProfileId());
		post.setCompanyProfile(compF);
		post.setDescription(dto.getDescription());
		post.setJobRequirments(dto.getJobRequirments());
		post.setLocation(dto.getLocation());
		post.setEmploymentType(dto.getEmploymentType());
		Employer emp=new Employer();
		emp.setId(dto.getEmployerId());
		post.setEmployer(emp);
//		post.setFieldCount(dto.getFieldCount());
//		post.setPostFields(dto.getPostFields());
//		post.setApplications(dto.getApplications());
//		post.setApplicationCount(dto.getApplicationCount());
		return post;
	}
	
	public static postDTO mapPostTODTO(Post post)
	{
		try {
			postDTO dto=new postDTO();
			dto.setId(post.getId());
			dto.setApplicationCount(post.getApplicationCount());
			dto.setEmployerUserName(post.getEmployer().getUserName());
			dto.setProfileId(post.getCompanyProfile().getId());
			dto.setAdminUserName(post.getCompanyProfile().getCompanyAdmin().getUserName());
			dto.setApplications(post.getApplications());
			dto.setFieldCount(post.getFieldCount());
//			dto.setPostFields(post.getPostFields());
			dto.setCreatedBy(post.getCreatedBy());
			dto.setCreatedDate(post.getCreatedDate());
			dto.setLastModifiedBy(post.getLastModifiedBy());
			dto.setLastModifiedDate(post.getLastModifiedDate());
			dto.setTitle(post.getTitle());
			companyProfile compF=post.getCompanyProfile();
			dto.setCompanyName(compF.getCompanyAdmin().getCompanyName());
			dto.setDescription(post.getDescription());
			dto.setJobRequirments(post.getJobRequirments());
			dto.setLocation(post.getLocation());
			dto.setEmploymentType(post.getEmploymentType());
			Employer emp=post.getEmployer();
			dto.setEmployerId(emp.getId());
			dto.setApplications(post.getApplications());
			dto.setFieldCount(post.getFieldCount());
			dto.setApplicationCount(post.getApplicationCount());
			dto.setPostField(post.getPostFields().get(0));
			dto.setField(post.getPostFields().get(0).getId());
			
			return dto;
		} catch (Exception e) {
			System.out.println("Error at mapPost To Dto : "+e);
			return null;
		}
	}
	public static postDTO mapPostTODTOForInsert(Post post)
	{
		postDTO dto=new postDTO();
		dto.setApplicationCount(post.getApplicationCount());
		dto.setApplications(post.getApplications());
		dto.setFieldCount(post.getFieldCount());
//		dto.setPostFields(post.getPostFields());
		dto.setCreatedBy(post.getCreatedBy());
		dto.setCreatedDate(post.getCreatedDate());
		dto.setLastModifiedBy(post.getLastModifiedBy());
		dto.setLastModifiedDate(post.getLastModifiedDate());
		dto.setTitle(post.getTitle());
		companyProfile compF=post.getCompanyProfile();
		dto.setCompanyName(compF.getCompanyAdmin().getCompanyName());
		dto.setDescription(post.getDescription());
		dto.setJobRequirments(post.getJobRequirments());
		dto.setLocation(post.getLocation());
		dto.setEmploymentType(post.getEmploymentType());
		Employer emp=post.getEmployer();
		dto.setEmployerId(emp.getId());
		dto.setApplications(post.getApplications());
		dto.setFieldCount(post.getFieldCount());
		dto.setApplicationCount(post.getApplicationCount());
		dto.setPostField(post.getPostFields().get(0));
		dto.setField(post.getPostFields().get(0).getId());
		return dto;
	}
	
	private static postFieldDTO convertPostFieldToDto(postField postF)
	{
		return postFieldMapper.mapPostFieldDtoToPostFieldForPost(postF);
	}
}
