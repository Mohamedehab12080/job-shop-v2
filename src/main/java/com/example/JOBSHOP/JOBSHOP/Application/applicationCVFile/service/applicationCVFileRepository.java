package com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface applicationCVFileRepository extends JpaRepository<applicationCVFileDTO, Long>{

	
}
