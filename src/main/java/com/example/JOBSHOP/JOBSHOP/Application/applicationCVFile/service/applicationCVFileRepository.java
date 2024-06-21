package com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile.applicationCVFile;

@Repository
public interface applicationCVFileRepository extends JpaRepository<applicationCVFile, Long>{

	
}
