package com.example.JOBSHOP.JOBSHOP.degrees.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.degrees.Qualification;

@Repository
public interface qualificationRepository extends JpaRepository<Qualification,Long>{

	Qualification findByQualificationName(String qualificationName);
	
	@Query(value="select qual from Qualification qual where qual.qualificationName like %:qualificationName%")
	List<Qualification>findByQualificationNameLike(@Param("qualificationName") String qualificationName);
	
	@Query(value ="SELECT q.qualificationName FROM Qualification q")
	List<String> findAllQualificationName();
	
}
