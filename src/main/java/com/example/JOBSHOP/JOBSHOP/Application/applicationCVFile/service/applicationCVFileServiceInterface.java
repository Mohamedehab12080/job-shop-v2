package com.example.JOBSHOP.JOBSHOP.Application.applicationCVFile.service;

public interface applicationCVFileServiceInterface {

	public applicationCVFileDTO insert(applicationCVFileDTO entity);
	public applicationCVFileDTO findByApplicationId(Long applicationId);
	public void Delete(Long id);
	 
}
