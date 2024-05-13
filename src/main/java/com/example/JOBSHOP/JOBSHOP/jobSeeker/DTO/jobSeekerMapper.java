package com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import java.util.List;
public class jobSeekerMapper {

	public static jobSeeker mapDTOToJobSeeker(jobSeekerDTO jobSeeker) throws ParseException
	{
		
		jobSeeker dto=new jobSeeker();
		dto.setId(jobSeeker.getId());
		dto.setAddress(jobSeeker.getAddress());
		dto.setContacts(jobSeeker.getContacts());
		dto.setCreatedDate(jobSeeker.getCreatedDate());
		dto.setEmail(jobSeeker.getEmail());
		dto.setPassword(jobSeeker.getPassword());
		dto.setUserName(jobSeeker.getUserName());
		dto.setUserType(Role.jobSeeker);
		dto.setEducation(jobSeeker.getEducation());
		dto.setApplications(jobSeeker.getApplications());
		dto.setExperience(jobSeeker.getExperience());
		dto.setApplicationCount(jobSeeker.getApplicationCount());
		dto.setJobSeekerSkills(jobSeeker.getJobSeekerSkills());
		dto.setJobSeekerQualifications(jobSeeker.getJobSeekerQualifications());
		dto.setFollowers(toUsers(jobSeeker.getFollowers()));
		dto.setFollowings(toUsers(jobSeeker.getFollowings()));
		dto.setLogin_with_google(jobSeeker.isIs_signin_with_google());
		dto.setReq_user(jobSeeker.isReq_user());
		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
		dto.setDescription(jobSeeker.getDescription());
		dto.setGender(jobSeeker.getGender());
		dto.setCoverImage(jobSeeker.getCoverImage());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dto.setBirthDate(dateFormat.parse(jobSeeker.getBirthDate().substring(0,9)));
		return dto;
	}
	
	public static jobSeekerDTO mapJobSeekerToDTO(jobSeeker jobSeeker)
	{
		jobSeekerDTO dto=new jobSeekerDTO();
		List<String> namesOfSkills=new ArrayList<>();
		List<String> namesOfQual=new ArrayList<String>();
		dto.setId(jobSeeker.getId());
		dto.setAddress(jobSeeker.getAddress());
		dto.setContacts(jobSeeker.getContacts());
		dto.setEmail(jobSeeker.getEmail());
		dto.setPassword(jobSeeker.getPassword());
		System.out.println("user Password : "+jobSeeker.getPassword());
		dto.setUserName(jobSeeker.getUserName());
		dto.setUserType(Role.jobSeeker);
		dto.setEducation(jobSeeker.getEducation());
		dto.setApplications(jobSeeker.getApplications());
		dto.setApplicationCount(jobSeeker.getApplicationCount());
		for(jobSeekerSkill jobSeekerSkill:jobSeeker.getJobSeekerSkills())
		{
			namesOfSkills.add(jobSeekerSkill.getSkill().getSkillName());
		}
		for(jobSeekerQualification jobSeekerQual:jobSeeker.getJobSeekerQualifications())
		{
			namesOfQual.add(jobSeekerQual.getQualification().getQualificationName());
		}
		dto.setSkills(namesOfSkills);
		dto.setQualifications(namesOfQual);
		dto.setJobSeekerSkills(jobSeeker.getJobSeekerSkills());
		dto.setJobSeekerQualifications(jobSeeker.getJobSeekerQualifications());
		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
		dto.setPicture(jobSeeker.getPicture());
		dto.setFollowers(toUserDtos(jobSeeker.getFollowers()));
		dto.setFollowings(toUserDtos(jobSeeker.getFollowings()));
		dto.setIs_signin_with_google(jobSeeker.isLogin_with_google());
		dto.setReq_user(jobSeeker.isReq_user());
		dto.setDescription(jobSeeker.getDescription());
		dto.setGender(jobSeeker.getGender());
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		dto.setBirthDate(df.format(jobSeeker.getBirthDate()));
		dto.setExperience(jobSeeker.getExperience());
		dto.setCoverImage(jobSeeker.getCoverImage());
		System.out.println("jobSeeker BirthDate : "+df.format(jobSeeker.getBirthDate()));
		return dto;
	}

	
	public static List<User> toUsers(List<UserDTO> reqUser)
	{
		List<User> jobSeekerDtos=new ArrayList<User>();
		for(UserDTO jobSeeker:reqUser)
		{
			User dto=new User();
			dto.setId(jobSeeker.getId());
			dto.setAddress(jobSeeker.getAddress());
			dto.setContacts(jobSeeker.getContacts());
			dto.setEmail(jobSeeker.getEmail());
			dto.setPassword(jobSeeker.getPassword());
			dto.setUserName(jobSeeker.getUserName());
			dto.setUserType(Role.jobSeeker);
			dto.setPicture(jobSeeker.getPicture());
			dto.setGender(jobSeeker.getGender());
			dto.setCoverImage(jobSeeker.getCoverImage());
			jobSeekerDtos.add(dto);
		}
		return jobSeekerDtos;
	}
	public static List<UserDTO> toUserDtos(List<User> reqUser)
	{
		List<UserDTO> jobSeekerDtos=new ArrayList<UserDTO>();
		for(User jobSeeker:reqUser)
		{
			UserDTO dto=new UserDTO();
			dto.setId(jobSeeker.getId());
			dto.setAddress(jobSeeker.getAddress());
			dto.setContacts(jobSeeker.getContacts());
			dto.setEmail(jobSeeker.getEmail());
			dto.setPassword(jobSeeker.getPassword());
			dto.setUserName(jobSeeker.getUserName());
			dto.setUserType(Role.jobSeeker);
			dto.setPicture(jobSeeker.getPicture());
			dto.setGender(jobSeeker.getGender());
			dto.setCoverImage(jobSeeker.getCoverImage());
			jobSeekerDtos.add(dto);
		}
		return jobSeekerDtos;
	}

	public static List<jobSeeker> toJobSeekers(List<jobSeekerDTO> reqUser) throws ParseException
	{
			List<jobSeeker> jobSeekerDtos=new ArrayList<jobSeeker>();
			for(jobSeekerDTO jobSeeker:reqUser)
			{
				jobSeeker dto=new jobSeeker();
				dto.setId(jobSeeker.getId());
				dto.setAddress(jobSeeker.getAddress());
				dto.setContacts(jobSeeker.getContacts());
				dto.setEmail(jobSeeker.getEmail());
				dto.setPassword(jobSeeker.getPassword());
				dto.setUserName(jobSeeker.getUserName());
				dto.setUserType(Role.jobSeeker);
				dto.setEducation(jobSeeker.getEducation());
				dto.setApplications(jobSeeker.getApplications());
				dto.setApplicationCount(jobSeeker.getApplicationCount());
				dto.setJobSeekerSkills(jobSeeker.getJobSeekerSkills());
				dto.setJobSeekerQualifications(jobSeeker.getJobSeekerQualifications());
				dto.setEmploymentState(jobSeeker.getEmploymentState()); 
				dto.setPicture(jobSeeker.getPicture());
				dto.setGender(jobSeeker.getGender());
				dto.setExperience(jobSeeker.getExperience());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dto.setBirthDate(dateFormat.parse(jobSeeker.getBirthDate().substring(0,9)));
				dto.setCoverImage(jobSeeker.getCoverImage());

				jobSeekerDtos.add(dto);
			}
			return jobSeekerDtos;
				
	}
	
	public static List<jobSeekerDTO> toJobSeekerDTos(List<jobSeeker> reqUSer) {
		List<jobSeekerDTO> jobSeekerDtos=new ArrayList<jobSeekerDTO>();
		for(jobSeeker jobSeeker:reqUSer)
		{
			jobSeekerDTO dto=new jobSeekerDTO();
			List<String> namesOfSkills=new ArrayList<>();
			dto.setId(jobSeeker.getId());
			dto.setAddress(jobSeeker.getAddress());
			dto.setContacts(jobSeeker.getContacts());
			dto.setEmail(jobSeeker.getEmail());
			dto.setPassword(jobSeeker.getPassword());
			dto.setUserName(jobSeeker.getUserName());
			dto.setUserType(Role.jobSeeker);
			dto.setEducation(jobSeeker.getEducation());
			dto.setApplications(jobSeeker.getApplications());
			dto.setApplicationCount(jobSeeker.getApplicationCount());
			for(jobSeekerSkill jobSeekerSkill:jobSeeker.getJobSeekerSkills())
			{
				namesOfSkills.add(jobSeekerSkill.getSkill().getSkillName());
			}
			dto.setSkills(namesOfSkills);
			dto.setEmploymentState(jobSeeker.getEmploymentState()); 
			dto.setPicture(jobSeeker.getPicture());
			dto.setGender(jobSeeker.getGender());
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			dto.setBirthDate(df.format(jobSeeker.getBirthDate()));
			dto.setExperience(jobSeeker.getExperience());
			System.out.println("jobSeeker BirthDate : "+df.format(jobSeeker.getBirthDate()));
			dto.setCoverImage(jobSeeker.getCoverImage());
			jobSeekerDtos.add(dto);
		}
		return jobSeekerDtos;
	}
}
