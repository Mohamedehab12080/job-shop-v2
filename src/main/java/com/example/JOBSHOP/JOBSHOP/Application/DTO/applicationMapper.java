package com.example.JOBSHOP.JOBSHOP.Application.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillDTO;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillMapper;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

public class applicationMapper {

	
	private final postServiceInterface postServiceI;

	@Autowired
	public applicationMapper(postServiceInterface postServiceI) {
	    this.postServiceI = postServiceI;
	}

	
	public static applicationDTO mapApplicationToDTO(Application app) {
		
		applicationDTO dto=new applicationDTO();
		List<String> skills=new ArrayList<String>();
		List<String> qualifications=new ArrayList<String>();
//		List<applicationQualificationDTO> appQualList=new ArrayList<applicationQualificationDTO>();
//		List<applicationSkillDTO> appSkillList=new ArrayList<applicationSkillDTO>();
//		for(applicationQualification appQual:app.getApplicationQualifications())
//		{
//			appQualList.add(convertApplicationQualificationToDTO(appQual));
//		}
//		for(applicationSkill appSKill:app.getApllicationSkills())
//		{
//			appSkillList.add(convertDtoToApplicationSkill(appSKill));
//		}
		for(applicationSkill jobSeekerSkill:app.getApllicationSkills())
		{
			skills.add(
					jobSeekerSkill
					.getJobSeekerSkill()
					.getSkill()
					.getSkillName());	
		}
		
		for(applicationQualification jobSeekerQualification:app.getApplicationQualifications())
		{
			qualifications.add(
					jobSeekerQualification.
					getJobSeekerQualification()
					.getQualification()
					.getQualificationName());	
		}
		
//		dto.setApplicationQualifications(appQualList);
//		dto.setApplicationSkills(appSkillList);
		dto.setSkills(skills);
		dto.setQualifications(qualifications);
		dto.setCreatedBy(app.getCreatedBy());
		dto.setExperience(app.getExperience());
		dto.setCreatedDate(app.getCreatedDate());
		dto.setLastModifiedBy(app.getLastModifiedBy());
		dto.setLastModifiedDate(app.getLastModifiedDate());
		dto.setStatuseCode(app.getStatusCode());
		dto.setId(app.getId());
		
//		if(app.getPost()!=null)
//		{
			dto.setPostId(app.getPost().getId());
			dto.setPostExperienc(app.getPost().getExperience());
//			dto.setPostSkills(app.getPost());
//			dto.setPost(app.getPost());
//		}
//		
		
		if (app.getJobSeeker() != null) {
			dto.setJobSeekerId(app.getJobSeeker().getId());
			dto.setJobSeekerUserName(app.getJobSeeker().getUserName());
			dto.setJobSeekerPicture(app.getJobSeeker().getPicture());
			dto.setJobSeekerAddress(app.getJobSeeker().getAddress());
			dto.setJobSeekerEducation(app.getJobSeeker().getEducation());
			dto.setJobSeekerr(app.getJobSeeker());
			dto.setJobSeekerEmail(app.getJobSeeker().getEmail());
		}
			
		return dto;
	}
public applicationDTO mapApplicationToDTOIncludingPostSkillsAndQualifications(Application app) {
		
		applicationDTO dto=new applicationDTO();
		
		List<String> skills=new ArrayList<String>();
		List<String> qualifications=new ArrayList<String>();
//		List<applicationQualificationDTO> appQualList=new ArrayList<applicationQualificationDTO>();
//		List<applicationSkillDTO> appSkillList=new ArrayList<applicationSkillDTO>();
//		for(applicationQualification appQual:app.getApplicationQualifications())
//		{
//			appQualList.add(convertApplicationQualificationToDTO(appQual));
//		}
//		for(applicationSkill appSKill:app.getApllicationSkills())
//		{
//			appSkillList.add(convertDtoToApplicationSkill(appSKill));
//		}
		for(applicationSkill jobSeekerSkill:app.getApllicationSkills())
		{
			skills.add(
					jobSeekerSkill
					.getJobSeekerSkill()
					.getSkill()
					.getSkillName());	
		}
		
		for(applicationQualification jobSeekerQualification:app.getApplicationQualifications())
		{
			qualifications.add(
					jobSeekerQualification.
					getJobSeekerQualification()
					.getQualification()
					.getQualificationName());	
		}
		
//		dto.setApplicationQualifications(appQualList);
//		dto.setApplicationSkills(appSkillList);
		dto.setSkills(skills);
		dto.setQualifications(qualifications);
		dto.setCreatedBy(app.getCreatedBy());
		dto.setExperience(app.getExperience());
		dto.setCreatedDate(app.getCreatedDate());
		dto.setLastModifiedBy(app.getLastModifiedBy());
		dto.setLastModifiedDate(app.getLastModifiedDate());
		dto.setStatuseCode(app.getStatusCode());
		dto.setId(app.getId());
		
//		if(app.getPost()!=null)
//		{
			dto.setPostId(app.getPost().getId());
			postDTO postDto=postMapper.mapPostTODTO(postServiceI.findById(app.getPost().getId()));
			dto.setPostSkills(postDto.getSkills());
			dto.setPostQualifications(postDto.getQualifications());
			dto.setPostExperienc(postDto.getExperience());
			dto.setCompanyName(postDto.getCompanyName());
			if(postDto.getTitle()!=null && !postDto.getTitle().equals("") && postDto.getTitle().contains("{"))
			{
				dto.setPostTitle(postDto.getTitle().substring(0,postDto.getTitle().indexOf("{"))); 
			}else 
			{
				dto.setPostTitle(postDto.getTitle().substring(0,postDto.getTitle().indexOf(",")));
			}
//			dto.setPostSkills(app.getPost());
//			dto.setPost(app.getPost());
//		}
//		
		
		if (app.getJobSeeker() != null) {
			dto.setJobSeekerId(app.getJobSeeker().getId());
			dto.setJobSeekerUserName(app.getJobSeeker().getUserName());
			dto.setJobSeekerPicture(app.getJobSeeker().getPicture());
			dto.setJobSeekerAddress(app.getJobSeeker().getAddress());
			dto.setJobSeekerEducation(app.getJobSeeker().getEducation());
			dto.setJobSeekerr(app.getJobSeeker());
			dto.setJobSeekerEmail(app.getJobSeeker().getEmail());
		}
		
		return dto;
	}

	public static Application mapDTOToApplication(applicationDTO dto) {
		
		Application app=new Application();
		List<applicationQualification> appQualList=new ArrayList<applicationQualification>();
		List<applicationSkill> appSkillList=new ArrayList<applicationSkill>();
		for(applicationQualificationDTO dtoQual:dto.getApplicationQualifications())
		{
			appQualList.add(convertDTOTopplicationQualification(dtoQual));
		}
		for(applicationSkillDTO appSKill:dto.getApplicationSkills())
		{
			appSkillList.add(convertDtoToApplicationSkill(appSKill));
		}
		app.setCreatedBy(dto.getCreatedBy());
		if(dto.getJobSeekerr()!=null)
		{
			app.setJobSeeker(dto.getJobSeekerr());
		}else
		{
			jobSeeker jobs=new jobSeeker();
			jobs.setId(dto.getJobSeekerId());
			app.setJobSeeker(jobs);
		}
		app.setExperience(dto.getExperience());
		if(dto.getPost()!=null)
		{
			app.setPost(dto.getPost());
		}else
		{
			Post post=new Post();
			post.setId(dto.getPostId());
			app.setPost(post);
		}
		
		
		app.setCreatedDate(dto.getCreatedDate());
		app.setLastModifiedBy(dto.getLastModifiedBy());
		app.setLastModifiedDate(dto.getLastModifiedDate());
		app.setStatusCode(dto.getStatuseCode());
		app.setId(dto.getId());
		app.setApplicationQualifications(appQualList);
		app.setApllicationSkills(appSkillList);
		app.setExperience(dto.getExperience());
		return app;
	}
	
	
public static Application mapDTOToApplicationForInsertApplicaiton(applicationDTO dto) {
		
		Application app=new Application();
		app.setCreatedBy(dto.getCreatedBy());
		if(dto.getJobSeekerr()!=null)
		{
			app.setJobSeeker(dto.getJobSeekerr());
		}else
		{
			jobSeeker jobs=new jobSeeker();
			jobs.setId(dto.getJobSeekerId());
			app.setJobSeeker(jobs);
		}
		
		if(dto.getPost()!=null)
		{
			app.setPost(dto.getPost());
		}else
		{
			Post post=new Post();
			post.setId(dto.getPostId());
			app.setPost(post);
		}
		
		app.setExperience(dto.getExperience());
		app.setCreatedDate(dto.getCreatedDate());
		app.setLastModifiedBy(dto.getLastModifiedBy());
		app.setLastModifiedDate(dto.getLastModifiedDate());
		app.setStatusCode(dto.getStatuseCode());
		app.setId(dto.getId());
		return app;
	}
	private static applicationQualification convertDTOTopplicationQualification(applicationQualificationDTO dto)
	{
		return applicationQualificationMapper.mapDTOToApplicationQualification(dto);
	}
	
	private static applicationSkill convertDtoToApplicationSkill(applicationSkillDTO dto)
	{
		return applicationSkillMapper.mapDTOToApplicationSkill(dto);
	}
	
	private static applicationSkillDTO convertDtoToApplicationSkill(applicationSkill dto)
	{
		return applicationSkillMapper.mapApplicationSkillToDTO(dto);
	}
	private static applicationQualificationDTO convertApplicationQualificationToDTO(applicationQualification app)
	{
		return applicationQualificationMapper.mapApplicationQualificationToDTO(app);
	}
}
