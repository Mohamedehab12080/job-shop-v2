package com.example.JOBSHOP.JOBSHOP.Post.postField.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Post.postField.postField;

@Repository
public interface postFieldRepository extends JpaRepository<postField,Long>{

//	@Query("SELECT f from postField f where f.employerField.id=:empFieldId")
//	List<postField> findByEmployerFieldId(@Param("empFieldId") Long id);
}
