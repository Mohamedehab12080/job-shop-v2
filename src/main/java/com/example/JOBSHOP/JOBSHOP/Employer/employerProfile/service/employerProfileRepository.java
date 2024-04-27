package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Employer.employerProfile.employerProfile;

@Repository
public interface employerProfileRepository extends JpaRepository<employerProfile, Long>{
	
	
	Optional<employerProfile> findByEmployerId(Long id);
}
