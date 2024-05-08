package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

public interface jobSeekerQualificationServiceInterface {

	jobSeekerQualification insert (jobSeekerQualification jobSeekerQualification);
	Optional<jobSeekerQualification> findById(Long id);
	List<jobSeekerQualification> findByJobSeekerId(Long id);
	jobSeekerQualification findByQualificationId(Long id);
	void insertAll(List<jobSeekerQualification> jobSeekerQualificationsToInsert);
	String deleteById(Long id);
	List<jobSeekerQualification> findAll();
	String updateSkill(Long id, jobSeekerQualificationDTO qualificationDto);
	
	jobSeekerQualification findByJobSeekerIdAndQualificationId(Long jobSeekerId,Long qualificationId);
	
	void deleteAllForJobSeekerId(Long jobSeekerId);
	
}
