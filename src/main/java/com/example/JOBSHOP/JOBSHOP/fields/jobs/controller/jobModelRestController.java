package com.example.JOBSHOP.JOBSHOP.fields.jobs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.companyFieldJob;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTOMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service.companyFieldJobServiceInterface;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.service.jobServiceInterface;

@RestController
@RequestMapping("/api/jobModel")
public class jobModelRestController {

	@Autowired
	private userServiceInterface userServiceI;
	@Autowired
	private jobServiceInterface jobServiceI;
	
	@Autowired
	private companyFieldJobServiceInterface companyFieldJobServiceI;
	
	@Autowired
	private companyFieldServiceInterface companyFieldServiceI;
	
	@RequestMapping("/findAll")
	public ResponseEntity<?> findAllJobs(@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			return new ResponseEntity< >(jobServiceI.findJobModelNames(),HttpStatus.OK);
		}else 
		{
			throw new UserException("User not found for this token");
		}
	}
	
	@RequestMapping("/findAllAndCheckForEachCompany")
	public ResponseEntity<?> findAllAndCheckForEachCompany(@RequestHeader("Authorization") String jwt) throws UserException {
	    User user = userServiceI.findUserByJwt(jwt);
	    if (user != null && user.getUserType().name().equals("Admin")) {
	        // Fetch and map company fields to DTO
	        List<companyFieldDTO> companyfield = companyFieldServiceI.findCompanyFieldsWithAdminId(user.getId())
	                .stream()
	                .map(companyFieldMapper::mapCompanyFieldToDTO)
	                .collect(Collectors.toList());
	        
	        // Extract IDs from company fields
	        List<Long> companyFieldsId = companyfield.stream()
	                .map(companyFieldDTO::getId)
	                .collect(Collectors.toList());
	        
	        // Fetch and map company field jobs to DTO
	        List<companyFieldJobDTO> companyFieldJob = companyFieldJobServiceI.findByCompanyFieldIds(companyFieldsId)
	                .stream()
	                .map(companyFieldJobDTOMapper::mapcompanyFieldJobToDTO)
	                .collect(Collectors.toList());
	        
	        // Extract job names from company field jobs
	        List<String> checkNameList = companyFieldJob.stream()
	                .map(companyFieldJobDTO::getJobName)
	                .collect(Collectors.toList());
	        
	        // Get all job model names and filter out the ones in checkNameList
	        List<String> jobModelNames = jobServiceI.findJobModelNames()
	                .stream()
	                .filter(jobName -> !checkNameList.contains(jobName))
	                .collect(Collectors.toList());
	        
	        return new ResponseEntity<>(jobModelNames, HttpStatus.OK);
	    } else {
	        throw new UserException("User not found for this token");
	    }
	}

	
}
