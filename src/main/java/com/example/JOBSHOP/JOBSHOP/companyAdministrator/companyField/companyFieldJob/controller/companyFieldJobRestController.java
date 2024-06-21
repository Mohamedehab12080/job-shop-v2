package com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.DTO.companyFieldJobDTOMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.companyFieldJob.service.companyFieldJobServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.jobSkillModel;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTO;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTOMapper;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service.jobSkillModelServiceInterface;

@RestController
@RequestMapping("/api/companyFieldJob")
public class companyFieldJobRestController {

	@Autowired
	private userServiceInterface userServiceI;
	@Autowired
	private companyFieldJobServiceInterface companyFieldJobServiceI;
	@Autowired
	private jobSkillModelServiceInterface jobSkillModelServiceI;
	
	@RequestMapping("/findByCompanyFieldId{id}")
	public ResponseEntity<?> findByCompanyFieldId(@PathVariable Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			List<companyFieldJobDTO> dtos=companyFieldJobServiceI.findByCompanyFieldId(id).stream().map(companyFieldJobDTOMapper::mapcompanyFieldJobToDTO).collect(Collectors.toList());
			List<companyFieldJobDTO>updatedDTOS=new ArrayList<companyFieldJobDTO>();
			for(companyFieldJobDTO dtosJ:dtos)
			{
				List<jobSkillModelDTO> jobSkillDTO=jobSkillModelServiceI.findByJobModelId(dtosJ.getJobId()).stream().map(jobSkillModelDTOMapper::mapJobSkillModelToDTO).collect(Collectors.toList());
				List<String>skillsName=new ArrayList<String>();
				jobSkillDTO.stream().filter(skillJob->skillsName.add(skillJob.getSkillName()));
				dtosJ.setSkillsName(skillsName);
				updatedDTOS.add(dtosJ);
			}
			return new ResponseEntity< >(
					updatedDTOS,HttpStatus.OK);
		}else 
		{
			return new ResponseEntity< >("User not found for this token",HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
