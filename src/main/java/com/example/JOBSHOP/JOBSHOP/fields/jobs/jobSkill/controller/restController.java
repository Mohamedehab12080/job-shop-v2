package com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.controller;

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
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.DTO.jobSkillModelDTOMapper;
import com.example.JOBSHOP.JOBSHOP.fields.jobs.jobSkill.service.jobSkillModelServiceInterface;

@RestController
@RequestMapping("/api/jobSkill")
public class restController {

	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private jobSkillModelServiceInterface jobSkillServiceI;
	
	@RequestMapping("/findByJobModel/{id}")
	public ResponseEntity<?> findJobSkillsByJobModelId(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			return new ResponseEntity< >(jobSkillServiceI.findByJobModelId(id).stream().map(jobSkillModelDTOMapper::mapJobSkillModelToDTO).collect(Collectors.toList()),HttpStatus.OK);
		}else 
		{
			return new ResponseEntity< >("User not Found for this token",HttpStatus.NOT_ACCEPTABLE); 
		}
	}
}
