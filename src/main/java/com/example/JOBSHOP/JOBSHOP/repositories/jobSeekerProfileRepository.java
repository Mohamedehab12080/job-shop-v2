package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;

@Repository
public interface jobSeekerProfileRepository extends JpaRepository<jobSeekerProfile, Long>{

	List<jobSeekerProfile>findByJobSeeker(jobSeeker jobSeeker);
   Optional<jobSeekerProfile> findByJobSeeker_id(Long jobSeeker_id);
	
}
