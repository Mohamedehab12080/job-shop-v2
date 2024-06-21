package com.example.JOBSHOP.JOBSHOP.fields.jobs.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;

@Repository
public interface jobServiceRepository extends JpaRepository<jobModel, Long>{

	@Query("SELECT j FROM jobModel j WHERE j.name=:name")
	jobModel findByName(String name);
	
	@Query("SELECT Distinct j.name FROM jobModel j")
	List<String> findJobModelNames();
}
