package com.example.JOBSHOP.JOBSHOP.jobSeeker.service;

import java.sql.Blob;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

import jakarta.transaction.Transactional;
@Repository
public interface jobSeekerRepository extends /*baseRepo<jobSeeker, Long>*/ JpaRepository<jobSeeker,Long>{

	Optional<jobSeeker> findById(Long id);
	
//
//	@Query(value="select j.skills from  jobSeeker j where j.id=:id")
//	List<String> findSkillsById(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value="update jobSeeker set picture=:picture where id=:id")
	int updatePic(@Param("picture") Blob picture,@Param("id")Long id);


	Optional<jobSeeker> findByEmail(String email);
}
