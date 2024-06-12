package com.example.JOBSHOP.JOBSHOP.Registration.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.StackWalker.Option;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.JOBSHOP.JOBSHOP.Registration.event.listener.registrationCompleteEventListener;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.password.iPasswordResetTokenService;
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
import com.example.JOBSHOP.JOBSHOP.location.location;
import com.example.JOBSHOP.JOBSHOP.location.service.locationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.response.AuthResponse;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

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
	
	@Autowired
	private registrationCompleteEventListener registrationCompleteEventListener;
	
	@Autowired
	private locationServiceInterface locationServiceI;
		
	@GetMapping("/findAll")
	public ResponseEntity<List<location>> findAllLocations()
	{

			return new ResponseEntity<List<location>>(locationServiceI.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/findAllValues")
	public ResponseEntity<List<String>> findAllValues()throws UserException
	{
			return new ResponseEntity<List<String>>(locationServiceI.findAllValues(),HttpStatus.OK);
	}
	
@PostMapping("/jobSeeker/signup")
public ResponseEntity<AuthResponse> createJobSeekerHandler(
		@RequestBody registerUserRequest jobSeeker) throws UserException, ParseException, IOException{
		
		Optional<User> isEmailExists=userRepository.findByEmail(jobSeeker.getEmail());
		if(isEmailExists.isPresent())
		{
			throw new UserException("Email is already used with another account");
		}
		
		jobSeeker realJobSeeker=new jobSeeker();
		realJobSeeker.setPassword(passwordEncoder.encode(jobSeeker.getPassword()));
		if(jobSeeker.getAddress() !=null && !jobSeeker.getAddress().isEmpty())
		{
			location loc=locationServiceI.findByValue(jobSeeker.getAddress());
			if(loc ==null)
			{
				location locToInsert=new location();
				locToInsert.setLocationValue(jobSeeker.getAddress());
				locationServiceI.insert(locToInsert);
			}
			realJobSeeker.setAddress(jobSeeker.getAddress());
		}
		
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
		realJobSeeker.setGender(jobSeeker.getGender());
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
		if(companyAdmin.getAddress() !=null && !companyAdmin.getAddress().isEmpty())
		{
			location loc=locationServiceI.findByValue(companyAdmin.getAddress());
			if(loc ==null)
			{
				location locToInsert=new location();
				locToInsert.setLocationValue(companyAdmin.getAddress());
				locationServiceI.insert(locToInsert);
			}
			companyAdmin.setAddress(companyAdmin.getAddress());
		}
		realCompanyAdmin.setEmail(companyAdmin.getEmail());
		realCompanyAdmin.setUserName(companyAdmin.getUserName());
		realCompanyAdmin.setUserType(Role.Admin);
		realCompanyAdmin.setContacts(companyAdmin.getContacts());
		realCompanyAdmin.setVerification(new varification());
		realCompanyAdmin.setCompanyName(companyAdmin.getCompanyName());
		realCompanyAdmin.setVerification(new varification());
		realCompanyAdmin.setPicture(companyAdmin.getPicture());
		realCompanyAdmin.setDescription(companyAdmin.getDescription());
		realCompanyAdmin.setGender(companyAdmin.getGender());
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

	

	  @PostMapping("/request/{email}")
	    public ResponseEntity<?> requestReset(@PathVariable("email") String email) throws UnsupportedEncodingException, MessagingException {
	   
		  	System.out.println("The email :"+email);
	        Optional<User> user = userRepository.findByEmail(email);

	        if (!user.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }

	        String token = jwtProvider.generateTokenResetPassword(email);
	        registrationCompleteEventListener.
	        sendVerificationEmailPasswordReset(
	        		user.get(),
	        		"http://localhost:3000/reset-password/Bearer "+token);
	        return ResponseEntity.ok(Collections.singletonMap("token", token));
	    }
	  
	  
	  @PostMapping("/reset")
	    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
	        String token = request.get("token");
	        String newPassword = request.get("newPassword");

	        try {
	        	
	            String email = jwtProvider.getEmailFromToken(token);

	            System.out.println("Email : "+email);
	            Optional<User> user = userRepository.findByEmail(email);

	            if (! user.isPresent()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid token");
	            }
	            
	            User user2=user.get();
	            user2.setPassword(passwordEncoder.encode(newPassword)); // Ensure you hash the password in a real application
	            userRepository.save(user2);

	            return ResponseEntity.ok("Password reset successful");
	        } catch (RuntimeException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token");
	        }
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
