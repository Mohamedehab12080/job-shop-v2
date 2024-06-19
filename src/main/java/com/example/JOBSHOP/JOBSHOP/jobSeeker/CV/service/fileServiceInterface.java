package com.example.JOBSHOP.JOBSHOP.jobSeeker.CV.service;

import com.example.JOBSHOP.JOBSHOP.jobSeeker.CV.CVFile;

public interface fileServiceInterface {

	public CVFile insert(CVFile entity);
	public void delete(Long id);
	public CVFile findById(Long id);
	
}
