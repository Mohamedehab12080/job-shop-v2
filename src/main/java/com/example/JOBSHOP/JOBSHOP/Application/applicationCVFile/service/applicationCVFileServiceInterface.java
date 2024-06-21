package com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile.service;

import com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile.applicationCVFile;

public interface applicationCVFileServiceInterface {

	public applicationCVFile insert(applicationCVFile entity);
	public applicationCVFile findByApplicationId(Long applicationId);
	public void Delete(Long id);
	 
}
