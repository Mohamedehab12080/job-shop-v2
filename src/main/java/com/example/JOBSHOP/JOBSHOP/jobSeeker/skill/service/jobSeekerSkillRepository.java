package com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;

import java.util.List;
import java.util.Optional;
@Repository
public interface jobSeekerSkillRepository extends JpaRepository<jobSeekerSkill, Long>{
	
	List<jobSeekerSkill> findByjobSeekerId(Long id);
	Optional<jobSeekerSkill> findBySkillId(Long id);
	
	@Query(value = "select js from jobSeekerSkill js where js.jobSeeker.id=:jobSeeker_id and js.skill.id=:skill_id")
	Optional<jobSeekerSkill> findByJobSeekerIdAndSkillId(@Param("jobSeeker_id") Long id,@Param("skill_id") Long skillId);
}
