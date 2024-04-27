package com.example.JOBSHOP.JOBSHOP.Application.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.applicationReturnedSkillsAndQualifications;

public interface applicationServiceInerface {

	Application getReferenceById(Long id);

	List<Application> findAll();

	Application insert(Application t);

	Application findById(Long id);

	Application update(Application t);

	List<Application> insertAll(List<Application> entity);

	void deleteById(Long id);

	/**
	 * 
	 * @Auther BOB {}
	 * @Function find JobSeeker's submitted applications order by LIFO
	 */
	List<Application> findByJobSeekerIdOrderByCreatedDate(Long id);

	/**
	 * 
	 * @Author BOB
	 * @Function Get List <Application> for a specific Post from higher matched to lower matched for the Employer
	 */
	List<applicationDTO> getBestApplicationsForPost(postDTO Post);

	/**
	  * @author BOBO
	  * @param postSkills
	  * @param applicationSkills
	  * @return No for not insert and yes for insert and the remained skills for applier
	  */
	String returningTheRemainedSkills(List<String> postSkills, List<String> applicationSkills);

	String returningRemainedQualifications(List<String> postQualifications, List<String> applicationQualifications);

	List<String> returningRemainedSkillsForListOfPosts(List<String> postSkills, List<String> applicationSkills);

	List<String> returningRemainedQualificationsForPostList(List<String> postSkills, List<String> applicationSkills);

	Application findByJobSeekerIdAndPostId(Long jobSeekerId,Long postId);
}
