package com.example.JOBSHOP.JOBSHOP.Registration.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/homeRest")
@CrossOrigin("http://localhost:3000")
public class homeRestController {

	@Autowired
	private userServiceInterface userServiceI;
	

	@GetMapping("/Entered")
	public ResponseEntity<?> homePage(HttpSession session)
	{
		User user=(User)session.getAttribute("user");
			if(user!=null)
			{
				String userName=user.getEmail();
				System.out.println("User name fetched : "+userName);
				byte[] img=user.getPicture();
				if(img!=null)
				{
					System.out.println("Not empty Img : "+img);
				}else 
				{
					System.out.println("empty Img : ");

				}
				System.out.println("user Id : "+user.getId());
				return ResponseEntity.ok(user);	
			}
			
		
	
		return ResponseEntity.notFound().build();
	}
}
