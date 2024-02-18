package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.Application;

@Repository
public interface applicationRepository extends JpaRepository<Application,Long>{

	List<Application> findByJobSeekerId(Long id);
	
	
}
