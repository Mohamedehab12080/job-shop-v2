package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.service;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobQualifications.jobQualificationModel;

@Repository
public interface jobQualificationRepository extends JpaRepository<jobQualificationModel,Long>{
	
	List<jobQualificationModel> findByJobModelId(Long jobModelId);

	jobQualificationModel findByJobModelIdAndQualificationId(Long jobModelId, Long qualificationId);
}
