package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service;

import java.util.List;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;

public interface companyFieldJobServiceInterface {

	List<companyFieldJob> findByCompanyFieldId(Long id);
	companyFieldJob insert(companyFieldJob companyFieldJob);
	String delete (Long id);
	List<companyFieldJob> findByJobModelId(Long id);
	companyFieldJob findById(Long id);
	void insertAll(List<companyFieldJob> companyFieldJobs);
	companyFieldJob findByJobModelIdAndCompanyFieldId(Long jobModelId, Long companyModelId);
	List<companyFieldJob> findByCompanyFieldIds(List<Long> ids);
}
