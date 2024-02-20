package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.Employer;
@Repository
public interface employerRepository extends /*baseRepo<Employer,Long>*/ JpaRepository<Employer, Long>{

	@EntityGraph(attributePaths = {"companyAdministrator"})
	List<Employer> findByCompanyAdministratorId(Long CompanyAdministratorId);
	
	@Override
	@EntityGraph(attributePaths = {"companyAdministrator"})
	Optional<Employer> findById(Long id);
	
//	@Transactional
//	@Modifying(flushAutomatically = true,clearAutomatically = true)
//	@Query("Delete from Employer em where em.id=:id")
//	int deleteEmployer(@Param("id") Long id);
}
