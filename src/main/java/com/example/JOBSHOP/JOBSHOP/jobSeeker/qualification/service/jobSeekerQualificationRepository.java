package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;

@Repository
public interface jobSeekerQualificationRepository extends JpaRepository<jobSeekerQualification,Long>{

	List<jobSeekerQualification> findByJobSeekerId(Long id);

	jobSeekerQualification findByQualificationId(Long id);

}
