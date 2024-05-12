package com.example.JOBSHOP.JOBSHOP.jobSeeker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.controller.skillsAndQualificationsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.requests.saveSkillsRequest;

public interface jobSeekerServiceInterface {

	Optional<jobSeeker> findByEmail(String email) ;

	jobSeeker insert(jobSeeker convertJobSeekerDTO);

	String insertJobSeekerSkillsAndQualifications(saveSkillsRequest skillsRequest);

	jobSeeker findById(Long id);

	Object insertPicture(Long id, String picture);

	List<Application> findAllApplicationsForJobSeeker(Long id);

	applicationReturnedSkillsAndQualifications applyForPost(applicationDTO app);

	String insertJobSeekerSkillsAndQualificationsOptimized(saveSkillsRequest skillsRequest);

	List<postDTO> getPostsWithSkillsOnPublic(Long jobSeekerId);
	
	skillsAndQualificationsRequest getJobSeekerSkillsAndQualificaitonsByJobSeekerId(Long jobSeekerId);

	jobSeeker update(Long jobSeekerId, jobSeeker user);
} 
