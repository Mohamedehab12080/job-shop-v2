package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.Application;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
@Repository
public interface companyAdminRepository extends /*baseRepo<companyAdministrator, Long>*/ JpaRepository<companyAdministrator,Long>{

	Optional<companyAdministrator> findById(Long id);
	
	@Query("select com.id from companyAdministrator com where com.companyName=:companyName")
	Long findByCompanyName(@Param("companyName") String companyName);

}
