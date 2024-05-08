package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;

import jakarta.transaction.Transactional;

@Service
public class jobSeekerQualificationService implements jobSeekerQualificationServiceInterface{

	@Autowired
	private jobSeekerQualificationRepository jobSeekerQualificationRepo;
	
	@Override
	public jobSeekerQualification insert(jobSeekerQualification jobSeekerQualification) {
		
		return jobSeekerQualificationRepo.save(jobSeekerQualification);
	}

	@Override
	public Optional<jobSeekerQualification> findById(Long id) {
		return jobSeekerQualificationRepo.findById(id);
	}

	@Override
	public List<jobSeekerQualification> findByJobSeekerId(Long id) {
		return jobSeekerQualificationRepo.findByJobSeekerId(id);
	}

	@Override
	public jobSeekerQualification findByQualificationId(Long id) {
		return jobSeekerQualificationRepo.findByQualificationId(id);
	}

	@Override
	public void insertAll(List<jobSeekerQualification> jobSeekerQualificationsToInsert) {
		jobSeekerQualificationRepo.saveAll(jobSeekerQualificationsToInsert);
	}
	
	@Override
	public List<jobSeekerQualification> findAll() {
		return jobSeekerQualificationRepo.findAll();
	}
	
	@Transactional
	@Override
	public String updateSkill(Long id,jobSeekerQualificationDTO qualificationDto) {
		
		Optional<jobSeekerQualification> Search=findById(id);
		if(Search.isPresent())
		{
			jobSeekerQualification oldQualification=Search.get();
			jobSeekerQualification newQualification=convertToJobSeekerQualification(qualificationDto);
			if(newQualification.getQualification()!=null)
			{
				oldQualification.setQualification(newQualification.getQualification());
			}
			if(newQualification.getJobSeeker()!=null)
			{
				oldQualification.setJobSeeker(newQualification.getJobSeeker());
			}
			if(newQualification.getQualificationDegree()!=null)
			{
				oldQualification.setQualificationDegree(newQualification.getQualificationDegree());
			}
			jobSeekerQualificationRepo.save(oldQualification);
			return "updated";
		}else 
		{
			return "not found";
		}
		
	}
	
	private jobSeekerQualification convertToJobSeekerQualification(jobSeekerQualificationDTO qualificationDto)
	{
		return jobSeekerQualificationMapper.mapDtoToJobSeekerQualification(qualificationDto);
	}
	
	
	@Transactional
	@Override
	public String deleteById(Long id) {
		if(findById(id).isPresent())
		{
			jobSeekerQualificationRepo.deleteById(id);
			return "deleted";
		}else 
		{
			return "not found";
		}
	}

	@Override
	public jobSeekerQualification findByJobSeekerIdAndQualificationId(Long jobSeekerId, Long qualificationId) {
		
		if(jobSeekerQualificationRepo.findByJobSeekerIdAndQualificationId(jobSeekerId, qualificationId).isPresent())
		{
			return jobSeekerQualificationRepo.findByJobSeekerIdAndQualificationId(jobSeekerId, qualificationId).get();	
		}else 
		{
			return null;
		}
		 
	}

	@Transactional
	@Override
	public void deleteAllForJobSeekerId(Long jobSeekerId) {
		jobSeekerQualificationRepo.deleteAllForJobSeekerId(jobSeekerId);
		
	}
}
