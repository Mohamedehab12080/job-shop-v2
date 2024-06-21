package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;

@Service
public class companyFieldJobServiceImpl implements companyFieldJobServiceInterface{

	
	@Autowired
	private companyFieldJobRepository companyFieldJobRepository;
	
	@Override
	public List<companyFieldJob> findByCompanyFieldId(Long id) {
		return companyFieldJobRepository.findByCompanyFieldId(id);
	}

	@Override
	public companyFieldJob insert(companyFieldJob companyFieldJob) {
		return companyFieldJobRepository.save(companyFieldJob);
	}

	@Override
	public String delete(Long id) {
		if(findById(id)!=null)
		{
			companyFieldJobRepository.deleteById(id);
			return "Deleted";
		}else 
		{
			return "Not found";
		}
	}

	@Override
	public List<companyFieldJob> findByJobModelId(Long id) {
		return companyFieldJobRepository.findByJobModelId(id);
	}

	@Override
	public companyFieldJob findById(Long id) {
		return companyFieldJobRepository.findById(id).get();
	}

	@Override
	public void insertAll(List<companyFieldJob> companyFieldJobs) {
		companyFieldJobRepository.saveAll(companyFieldJobs);
	}

	@Override
	public companyFieldJob findByJobModelIdAndCompanyFieldId(Long jobModelId,Long companyModelId)
	{
		return companyFieldJobRepository.findByJobModelIdAndCompanyFieldId(jobModelId,companyModelId);
	}

	@Override
	public List<companyFieldJob> findByCompanyFieldIds(List<Long> ids) {
		
		return companyFieldJobRepository.findByCompanyFieldIds(ids);
	}
	
	
}
