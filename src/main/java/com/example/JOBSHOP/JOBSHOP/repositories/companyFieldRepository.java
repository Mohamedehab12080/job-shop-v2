package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
import com.example.JOBSHOP.JOBSHOP.models.companyField;
@Repository
public interface companyFieldRepository extends /*baseRepo<companyField, Long>*/ JpaRepository<companyField, Long>{
 
	List<companyField>findByCompanyAdministratorId(Long id);
	@Query("select f.id from companyField f where f.fieldName=:fieldName")
	Long findByFieldName(@Param("fieldName") String fieldName);
}
