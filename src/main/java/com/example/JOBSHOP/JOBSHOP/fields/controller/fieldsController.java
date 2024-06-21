package com.example.JOBSHOP.JOBSHOP.fields.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.DTO.companyFieldMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyField.service.companyFieldServiceInterface;
import com.example.JOBSHOP.JOBSHOP.fields.service.fieldServiceInterface;


@RestController
@RequestMapping("/api/fields")
public class fieldsController {


	@Autowired
	private fieldServiceInterface fieldServiceI;
	@Autowired
	private userServiceInterface userServiceI;

	@Autowired
	private companyFieldServiceInterface companyFieldServiceI;
	
		@GetMapping("/findAll")
		public ResponseEntity<List<String>> findAllSkillsDistinct(
				@RequestHeader("Authorization") String jwt) throws UserException
		{
			
			User user=userServiceI.findUserByJwt(jwt);
			if(user!=null)
			{
				return new ResponseEntity<List<String>>(fieldServiceI.findAllDistinct(),HttpStatus.OK);
			}else 
			{
				throw new UserException("user not found for this token");
			}
		}
		@GetMapping("/findAllForCompany")
		public ResponseEntity<List<String>> findAllFieldsForCompany(
		        @RequestHeader("Authorization") String jwt) throws UserException {

		    User user = userServiceI.findUserByJwt(jwt);
		    if (user != null && user.getUserType().name().equals("Admin")) {
		        // Fetch and map company fields to DTO
		        List<companyFieldDTO> compF = companyFieldServiceI.findCompanyFieldsWithAdminId(user.getId())
		                .stream()
		                .map(companyFieldMapper::mapCompanyFieldToDTO)
		                .collect(Collectors.toList());

		        // Print company field names
		        System.out.println("Company Fields:");
		        compF.forEach(companyFieldDTO -> System.out.println(companyFieldDTO.getFieldName()));

		        
		        // Extract field names from company fields
		        List<String> fieldNamesForCheck = compF.stream()
		                .map(companyFieldDTO::getFieldName)
		                .collect(Collectors.toList());

		        // Get all distinct field names and filter out the ones in fieldNamesForCheck
		        List<String> distinctFieldNames = fieldServiceI.findAllDistinct()
		                .stream()
		                .filter(fieldName -> !fieldNamesForCheck.contains(fieldName))
		                .collect(Collectors.toList());

		        System.out.println("Filtered Fields : "+distinctFieldNames);
		        return new ResponseEntity<>(distinctFieldNames, HttpStatus.OK);
		    } else {
		        throw new UserException("User not found for this token");
		    }
		}

}
