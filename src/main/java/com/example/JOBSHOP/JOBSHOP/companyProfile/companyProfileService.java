package com.example.JOBSHOP.JOBSHOP.companyProfile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.BaseService;

@Service
public class companyProfileService {

	
	@Autowired
	private companyProfileRepository compnayProfileRepository;
	
	 public companyProfile getReferenceById(Long id)
		{
			return compnayProfileRepository.getReferenceById(id);
		}
	   
		public List<companyProfile> findAll()
		{
			return compnayProfileRepository.findAll();
		}
		
		public companyProfile insert(companyProfile t)
		{
			return compnayProfileRepository.save(t);
		}
		
		public companyProfile findById(Long id)
		{
			Optional<companyProfile> finded=compnayProfileRepository.findById(id);
			if(finded.isPresent())
			{
				return finded.get();
			}else 
			{
				return null;
			}
			
		}
		
//		public void updateEntityStatus(Application t)
//		{
//			applicationRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		
		public companyProfile update(companyProfile t)
		{
			if(getReferenceById(t.getId())!=null)
			{
//				logInfo("Employer Updated Successfully");
				return compnayProfileRepository.save(t);
			}else 
			{
//				logError("EmployerNotFound");
				return null;
				
			}
		}

		/**
		 * 
		 * @author BOB
		 * @return companyProfile for specific companyAdmin
		 */
		public companyProfile findByCompanyAdmin(Long id)
		{
			return compnayProfileRepository.findByCompanyAdministratorId(id).get();

		}
		}

