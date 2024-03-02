package com.example.JOBSHOP.JOBSHOP.employerProfile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface employerProfileRepository extends JpaRepository<employerProfile, Long>{
	
	
	Optional<employerProfile> findByEmployerId(Long id);
}
