package com.example.JOBSHOP.JOBSHOP.Application.qualification.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;

@Repository
public interface applicationQualificationRepository extends JpaRepository<applicationQualification, Long>{

	List<applicationQualification> findByApplicationId(Long applicationId);
}
