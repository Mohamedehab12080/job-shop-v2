package com.example.JOBSHOP.JOBSHOP.fields.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.fields.Field;

@Repository
public interface fieldRepository extends JpaRepository<Field,Long>{

	Field findByFieldName(String fieldName);
	
	@Query(value = "Select f from Field f where f.fieldName like %:fieldName%")
	List<Field> findByFieldNameList(@Param("fieldName") String fieldName);
	
	@Query(value = "Select DISTINCT f.fieldName from Field f")
	List<String> findAllDistinctFieldNames();

	@Query(value="select f.id from Field f where f.fieldName=:fieldName")
	Long findIdByName(@Param("fieldName") String fieldName);
}
