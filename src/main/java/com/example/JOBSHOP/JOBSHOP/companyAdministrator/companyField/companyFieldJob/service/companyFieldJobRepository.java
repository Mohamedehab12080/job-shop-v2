package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;

@Repository
public interface companyFieldJobRepository extends JpaRepository<companyFieldJob, Long>{

	@Query(name="SELECT j FROM companyFieldJob j where j.companyField.id=:id")
	List<companyFieldJob> findByCompanyFieldId(Long id);

	@Query(name="SELECT j FROM companyFieldJob j where j.jobModel.id=:id")
	List<companyFieldJob> findByJobModelId(Long id);

	@Query(name="SELECT j FROM companyFieldJob j where j.jobModel.id=:jobModelId and j.companyField.id=:companyModelId")
	companyFieldJob findByJobModelIdAndCompanyFieldId(Long jobModelId, Long companyModelId);
	
	@Query("SELECT j FROM companyFieldJob j WHERE j.companyField.id IN :ids")
	List<companyFieldJob> findByCompanyFieldIds(@Param("ids") List<Long> ids);

}
