package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJobSkills.companyFieldJobSkill;

@Repository
public interface companyFieldJobSkillRepository extends JpaRepository<companyFieldJobSkill, Long>{

	
	@Query(name="SELECT s FROM companyFieldJobSkill s where s.companyFieldJobSkill.id=:id")
	List<companyFieldJobSkill> findBycompanyFieldJobSkillId(Long id);

	@Query(name="SELECT s FROM companyFieldJobSkill s where s.companyFieldJob.id=:id")
	List<companyFieldJobSkill> findByCompanyFieldJobId(Long companyFieldJob);
	
}
