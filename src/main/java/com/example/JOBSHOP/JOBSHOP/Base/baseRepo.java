package com.example.JOBSHOP.JOBSHOP.Base;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
@NoRepositoryBean
public interface baseRepo <T extends baseEntity<ID>, ID extends Number> 
extends JpaRepository<T, ID>{
	
	@Modifying
	@Transactional
	@Query("update #{#entityName} t SET t.statusCode = :statusCode where t.id = :id")
	void updateEntity (@Param("id") ID id , @Param("statusCode") String statusCode );
}