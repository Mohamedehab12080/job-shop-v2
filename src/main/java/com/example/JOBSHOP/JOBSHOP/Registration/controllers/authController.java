package com.example.JOBSHOP.JOBSHOP.Registration.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.security.jwtProvider;
import com.example.JOBSHOP.JOBSHOP.Registration.security.userRegistrationDetails;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.model.varification;
import com.example.JOBSHOP.JOBSHOP.User.service.userRepository;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdministratorServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.response.AuthResponse;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class authController {

	@Autowired
	private userRepository userRepository;
	@Autowired
	private jobSeekerServiceInterface jobSeekerServiceI;
	
	@Autowired
	private companyAdministratorServiceInterface companyAdminServiceI;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private jwtProvider jwtProvider;
	@Autowired
	private userRegistrationDetails userRegistrationDetails;
	
	@Autowired
	private skillServiceInterface skillServiceI;
	@Autowired
	private userServiceInterface userServiceI;
	
	
@PostMapping("/jobSeeker/signup")
public ResponseEntity<AuthResponse> createJobSeekerHandler(
		@RequestBody registerUserRequest jobSeeker) throws UserException, ParseException, IOException{
		
//		String email=jobSeeker.getEmail();
//		String password=jobSeeker.getPassword();
//		String userName=jobSeeker.getUserName();
//		String userType=jobSeeker.getUserType().name();
//		Date birthDate=jobSeeker.getBirthDate();
//		List<String> contacts=jobSeeker.getContacts();
		
		Optional<User> isEmailExists=userRepository.findByEmail(jobSeeker.getEmail());
		if(isEmailExists.isPresent())
		{
			throw new UserException("Email is already used with another account");
		}
		
		jobSeeker realJobSeeker=new jobSeeker();
		realJobSeeker.setPassword(passwordEncoder.encode(jobSeeker.getPassword()));
		realJobSeeker.setAddress(jobSeeker.getAddress());
		realJobSeeker.setEmail(jobSeeker.getEmail());
		realJobSeeker.setUserName(jobSeeker.getUserName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		realJobSeeker.setBirthDate(dateFormat.parse(jobSeeker.getBirthDate().substring(0,9)));
		realJobSeeker.setUserType(Role.jobSeeker);
		realJobSeeker.setExperience(jobSeeker.getExperience());
		realJobSeeker.setEmploymentState(jobSeeker.getEmploymentState());
		realJobSeeker.setDescription(jobSeeker.getDescription());
		realJobSeeker.setEducation(jobSeeker.getEducation());
		List<String> contacts=jobSeeker.getContacts();
		contacts.add(jobSeeker.getContact());
		realJobSeeker.setContacts(contacts);
		realJobSeeker.setVerification(new varification());
		realJobSeeker.setPicture(jobSeeker.getPicture());
		jobSeeker savedJobSeeker=jobSeekerServiceI.insert(realJobSeeker);
		Authentication authentication=new UsernamePasswordAuthenticationToken(jobSeeker.getEmail(), jobSeeker.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token,true);
		
		return new ResponseEntity<AuthResponse>(res,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/company/signup")
	public ResponseEntity<AuthResponse> createCompanyHandler(
			@RequestBody registerUserRequest companyAdmin) throws UserException, IOException{
		
//		String email=jobSeeker.getEmail();
//		String password=jobSeeker.getPassword();
//		String userName=jobSeeker.getUserName();
//		String userType=jobSeeker.getUserType().name();
//		Date birthDate=jobSeeker.getBirthDate();
//		List<String> contacts=jobSeeker.getContacts();
		
		Optional<User> isEmailExists=userRepository.findByEmail(companyAdmin.getEmail());
		if(isEmailExists.isPresent())
		{
			throw new UserException("Email is already used with another account");
		}
		
		companyAdministrator realCompanyAdmin=new companyAdministrator();
		realCompanyAdmin.setPassword(passwordEncoder.encode(companyAdmin.getPassword()));
		realCompanyAdmin.setAddress(companyAdmin.getAddress());
		realCompanyAdmin.setEmail(companyAdmin.getEmail());
		realCompanyAdmin.setUserName(companyAdmin.getUserName());
		realCompanyAdmin.setUserType(Role.Admin);
		realCompanyAdmin.setContacts(companyAdmin.getContacts());
		realCompanyAdmin.setVerification(new varification());
		realCompanyAdmin.setCompanyName(companyAdmin.getCompanyName());
		realCompanyAdmin.setVerification(new varification());
		realCompanyAdmin.setPicture(companyAdmin.getPicture());
		realCompanyAdmin.setDescription(companyAdmin.getDescription());
//		realCompanyAdmin.setPicture(picture.getBytes());
		companyAdministrator savedCompany=companyAdminServiceI.insert(realCompanyAdmin);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(companyAdmin.getEmail(), companyAdmin.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token,true);
		
		return new ResponseEntity<AuthResponse>(res,HttpStatus.CREATED);
	}


	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signIn(@RequestBody User user)
	{
		String userName=user.getEmail();
		String password=user.getPassword();
		
		Authentication authentication=authenticate(userName,password);
		
		String token=jwtProvider.generateToken(authentication);
		
		AuthResponse res=new AuthResponse(token,true);
		
		return new ResponseEntity<AuthResponse>(res,HttpStatus.ACCEPTED);
		
	}


	private Authentication authenticate(String userName, String password) {
		UserDetails userDetails=userRegistrationDetails.loadUserByUsername(userName);
		
		if(userDetails == null)
		{
			throw new BadCredentialsException("invalid username...");
		}
		if(!passwordEncoder.matches(password,userDetails.getPassword()))
		{
			throw new BadCredentialsException("invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
	
}
