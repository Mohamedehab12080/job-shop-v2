package com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;

@Repository
public interface jobSeekerQualificationRepository extends JpaRepository<jobSeekerQualification,Long>{

	List<jobSeekerQualification> findByJobSeekerId(Long id);

	jobSeekerQualification findByQualificationId(Long id);
	
	@Query(value = "select q from jobSeekerQualification q where q.jobSeeker.id=:jobSeeker_id and q.qualification.id=:qualification_id")
	Optional<jobSeekerQualification> findByJobSeekerIdAndQualificationId(
			@Param("jobSeeker_id") Long id,
			@Param("qualification_id") Long skillId);

	@Modifying
	@Query("DELETE FROM jobSeekerQualification q WHERE q.jobSeeker.id = :jobSeeker_id")
	void deleteAllForJobSeekerId(@Param("jobSeeker_id") Long jobSeeker_id);
}
