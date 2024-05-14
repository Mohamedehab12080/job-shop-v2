package com.example.JOBSHOP.JOBSHOP.fields.controller;

import java.util.List;

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
import com.example.JOBSHOP.JOBSHOP.fields.service.fieldServiceInterface;


@RestController
@RequestMapping("/api/fields")
public class fieldsController {


	@Autowired
	private fieldServiceInterface fieldServiceI;
	@Autowired
	private userServiceInterface userServiceI;

		@GetMapping("/findAll")
		public ResponseEntity<List<String>> findAllSkillsDistinct(
				@RequestHeader("Authorization") String jwt) throws UserException
		{
			
			User user=userServiceI.findUserByJwt(jwt);
			if(user!=null)
			{
				System.out.println("Skills at back end : "+fieldServiceI.findAllDistinct());
				return new ResponseEntity<List<String>>(fieldServiceI.findAllDistinct(),HttpStatus.OK);
			}else 
			{
				throw new UserException("user not found for this token");
			}
		}
}
