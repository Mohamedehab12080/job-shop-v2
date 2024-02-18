package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;

@Repository
public interface jobSeekerRepository extends JpaRepository<jobSeeker, Long>{

	Optional<jobSeeker> findById(Long id);
	

	@Query(value="select j.skills from  jobSeeker j where j.id=:id")
	List<String> findSkillsById(@Param("id") Long id);
	
}
