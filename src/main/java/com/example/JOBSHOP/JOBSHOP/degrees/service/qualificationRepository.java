package com.example.JOBSHOP.JOBSHOP.degrees.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.degrees.qualification;

@Repository
public interface qualificationRepository extends JpaRepository<qualification,Long>{

	qualification findByQualificationName(String qualificationName);
	
	@Query(name="select qual from qualification qual where qual.qualificationName %:qualificationName%")
	List<qualification>findByQualificationNameLike(@Param("qualificationName") String qualificationName);

	@Query(name="select Distinct qual.qualificationName from qualification qual")
 	List<String> findQualificationsDistinctNames();
	
}
