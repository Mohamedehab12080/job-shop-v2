package com.example.JOBSHOP.JOBSHOP.jobSeeker.CV.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.CV.CVFile;

@Repository
public interface fileRepository extends JpaRepository<CVFile,Long>{

}
