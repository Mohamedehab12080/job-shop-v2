package com.example.JOBSHOP.JOBSHOP.degrees;

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
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;

@RestController
@RequestMapping("/api/qualification")
public class controller {

	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private qualificationServiceInterface qualificationServiceI;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<String>> findAllQualifications(
			@RequestHeader("Authorization") String jwt
			) throws UserException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			return new ResponseEntity<List<String>>
			(qualificationServiceI.findAllDistinct()
					,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
}
