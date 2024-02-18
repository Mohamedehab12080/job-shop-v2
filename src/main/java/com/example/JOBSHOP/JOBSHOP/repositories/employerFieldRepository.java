package com.example.JOBSHOP.JOBSHOP.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.models.employerField;

@Repository
public interface employerFieldRepository extends JpaRepository<employerField,Long>{
   
	List<employerField> findByEmployerId(Long id);
}
