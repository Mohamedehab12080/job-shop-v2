package com.example.JOBSHOP.JOBSHOP.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;
@Repository
public interface jobSeekerProfileRepository extends /*baseRepo<jobSeekerProfile, Long>*/ JpaRepository<jobSeekerProfile,Long>{

   Optional<jobSeekerProfile> findByJobSeekerId(Long id); 
   Optional<jobSeekerProfile> findByJobSeeker_id(Long jobSeeker_id);
}
