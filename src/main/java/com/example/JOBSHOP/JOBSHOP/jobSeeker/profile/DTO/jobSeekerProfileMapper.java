package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.DTO;

import java.util.stream.Collectors;

import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillMapper;

public class jobSeekerProfileMapper {

	
	public static jobSeekerProfileDTO 
	mapJobSeekerProfileToDTo(jobSeekerProfile jobSPro)
	{
		jobSeekerProfileDTO dto=new jobSeekerProfileDTO();
		jobSeeker jobSeekerObj=jobSPro.getJobSeeker();
		dto.setId(jobSPro.getId());
		dto.setJobSeeker(jobSeekerObj);
		dto.setAddress(jobSeekerObj.getAddress());
		dto.setApplications(
				jobSeekerObj
				.getApplications()
				.stream()
				.map(applicationMapper::mapApplicationToDTO)
				.collect(Collectors.toList()));
		dto.setDescription(jobSeekerObj.getDescription());
		dto.setEducation(jobSeekerObj.getEducation());
		
		if(!jobSeekerObj.getFollowers().isEmpty())
		{
			dto.setUserFollowers(jobSeekerObj.getFollowers());
			dto.setUserFollowersCount(jobSeekerObj.getFollowers().size());
		}
		if(!jobSeekerObj.getFollowings().isEmpty())
		{
			dto.setUserFollowings(jobSeekerObj.getFollowings());
			dto.setUserFollowingsCount(jobSeekerObj.getFollowings().size());
		}
	  dto.setJobSeekerId(jobSeekerObj.getId());
	  dto.setEmail(jobSeekerObj.getEmail());
	  dto.setJobSeekerSkills(
			  jobSeekerObj
			  .getJobSeekerSkills()
			  .stream()
			  .map(jobSeekerSkillMapper::mapJobSeekerSkillToDTO)
			  .collect(Collectors.toList()));
	  dto.setJobSeekerQualifications(
			  jobSeekerObj
			  .getJobSeekerQualifications()
			  .stream()
			  .map(jobSeekerQualificationMapper::mapJobSeekerQualificationToDTO)
			  .collect(Collectors.toList())
			  );
	  
		return dto;
	}
}
