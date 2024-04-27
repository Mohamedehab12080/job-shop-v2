package com.example.JOBSHOP.JOBSHOP.Registration.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class homeController {
	
	@Autowired
	private userServiceInterface userServiceI;
	
	@GetMapping
	public String homePageMain(Model model)
	{
		return "home";
	}
	@GetMapping("/authorized")
	public String homePage(Model model)
	{
		String userName=userServiceI.getCurrentUsername();
		System.out.println("User name fetched : "+userName);
		if(userName.equals("anonymousUser"))
		{
			
		}else 
		{
			Optional<User> user=userServiceI.findByEmail(userName);
			User realUser=null;
			if(user.isPresent())
			{
				realUser=user.get();
				System.out.println("user Id : "+realUser.getId());
				model.addAttribute("User",realUser);
			}
			model.addAttribute("User",realUser);

		}
		return "home?main";
	}
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
//	@GetMapping("/login")
//	public String loginPage(HttpServletRequest request, Model model)
//	{
//		String email=request.getParameter("email");
//		String password=request.getParameter("password");
//		
//		Optional<User> fetched=userServiceI.findByEmail(email);
//		if(fetched.isPresent())
//		{
//			System.out.println("User name : "+fetched.get().getUserName());
//			HttpSession session=request.getSession();
//			session.setAttribute("user", fetched.get());
//			return "login";  //2:09 video
//		}else 
//		{
//			System.out.println("email from session : "+email);
//			return "login";
//		}
//		
//	}
	
	@GetMapping("/logout")
	public String logout()
	{
		return "home";
	}
	@GetMapping("/error")
	public String errorPage()
	{
		return "error";  //2:09 video
	}

}
