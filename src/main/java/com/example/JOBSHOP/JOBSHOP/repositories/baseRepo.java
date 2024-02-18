package com.example.JOBSHOP.JOBSHOP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.example.JOBSHOP.JOBSHOP.models.User;

@NoRepositoryBean
public interface baseRepo <T extends User, ID extends Number> 
extends JpaRepository<T, ID>{
	
	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query("update #{#entityName} t SET t.statusCode = :statusCode where t.id = :id")
	void updateEntity (@Param("id") Long id , @Param("statusCode") Integer statusCode );
}