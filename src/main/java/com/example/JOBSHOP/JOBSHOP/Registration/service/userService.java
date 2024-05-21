package com.example.JOBSHOP.JOBSHOP.Registration.service;

//import org.springframework.security.core.Authentication;


import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdministratorServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerServiceInterface;

//import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userRepository;
import com.example.JOBSHOP.JOBSHOP.Registration.tokens.verificationTokenRepository;
import jakarta.transaction.Transactional;
import com.example.JOBSHOP.JOBSHOP.Registration.tokens.verificationToken;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.updateContactsRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.exceptions.UserAlreadyExistsException;
import com.example.JOBSHOP.JOBSHOP.Registration.security.jwtProvider;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
@Service
public class userService implements userServiceInterface{

	
	@Autowired 
	private  userRepository userRepository;
	@Autowired
	private verificationTokenRepository verificationTokenRepository;
	@Autowired
	private companyAdministratorServiceInterface companyAdministratorServiceI;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private jobSeekerServiceInterface jobSeekerServiceI;
	
	@Autowired
	private jwtProvider jwtProvider;
	
//	@Autowired
//	private ApplicationEventPublisher applicationPublisher; //application publisher that publish the event for specific class
//	
	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	
	
	/**
	 * @author BOB
	 * @function companyAdministrator registration with companyAdministratorDTO
	 */
	@Override
	public companyAdministrator registerCompanyAdministrator(companyAdministratorDTO companyAdministratorDto)
	{
		Optional<companyAdministrator> company=companyAdministratorServiceI.findByEmail(companyAdministratorDto.getEmail());
		if(company.isPresent())
		{
			throw new UserAlreadyExistsException(
					"User with email "+companyAdministratorDto.getEmail()+" already exists"
					);	
		}
		return companyAdministratorServiceI.insert(convertCompanyAdminDTo(companyAdministratorDto));
	}
	
	/**
	 * @author BOB
	 * @function jobSeeker registration with jobSeekerDto
	 */
	@Override
	public jobSeeker registerJobSeeker(jobSeekerDTO jobSeekerDTO)
	{
		
		Optional<jobSeeker> jobSeeker=jobSeekerServiceI.findByEmail(jobSeekerDTO.getEmail());
		if(jobSeeker.isPresent())
		{
			throw new UserAlreadyExistsException(
					"User with email "+jobSeekerDTO.getEmail()+" already exists"
					);
			
		}
		
		return jobSeekerServiceI.insert(convertJobSeekerDTO(jobSeekerDTO));
	}
	
	private companyAdministrator convertCompanyAdminDTo(companyAdministratorDTO companyAdmin)
	{
			companyAdministrator dto=new companyAdministrator();
			dto.setId(companyAdmin.getId());
			dto.setAddress(companyAdmin.getAddress());
			dto.setCompanyName(companyAdmin.getCompanyName());
			dto.setContacts(companyAdmin.getContacts());
			dto.setPicture(companyAdmin.getPicture());
			dto.setEmail(companyAdmin.getEmail());
			dto.setPassword(passwordEncoder.encode(companyAdmin.getPassword()));
			dto.setUserName(companyAdmin.getUserName());
			dto.setGender(companyAdmin.getGender());
//			dto.setCompanyFields(companyAdmin.getCompanyFields());
//			dto.setEmployers(companyAdmin.getEmployers());
			dto.setUserType(companyAdmin.getUserType());
			return dto;
		
	}
	
	private jobSeeker convertJobSeekerDTO(jobSeekerDTO jobSeeker)
	{
		jobSeeker dto=new jobSeeker();
		dto.setId(jobSeeker.getId());
		dto.setAddress(jobSeeker.getAddress());
		dto.setContacts(jobSeeker.getContacts());
		dto.setEmail(jobSeeker.getEmail());
		dto.setPassword(jobSeeker.getPassword());
//		dto.setPassword(passwordEncoder.encode(jobSeeker.getPassword()));
		dto.setUserName(jobSeeker.getUserName());
		dto.setUserType(Role.jobSeeker);
		dto.setEducation(jobSeeker.getEducation());
		dto.setApplications(jobSeeker.getApplications());
		dto.setApplicationCount(jobSeeker.getApplicationCount());
		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
		dto.setGender(jobSeeker.getGender());
		dto.setPicture(jobSeeker.getPicture());
		return dto;
	
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	
	@Override  // save verification token with userid 
	public void saveUserVerificationToken( User theUser, String verificationToken) {
		var VerificationToken=new verificationToken(verificationToken,theUser);
		verificationTokenRepository.save(VerificationToken);
	}

	
	@Override
	public verificationToken findVerificationToken(String verificationToken) {
		Optional<verificationToken> optionalToken=verificationTokenRepository.findByToken(verificationToken);
		if(optionalToken.isPresent())
		{
			return optionalToken.get();
		}else 
		{
			return null;
		}
	}

	

	/**
	 * @author BOB
	 * @Function check on the token expiration and if not expired then it will update user to be enabled = true
	 */
	@Override
	public String validateToken(String theToken) {
		Optional<verificationToken> token=verificationTokenRepository.findByToken(theToken);
		verificationToken tokenClass=token.get();
		if(!token.isPresent())
		{
			return "Invalid";
		}
		
		User user=tokenClass.getUser(); // getting user entity from token and initialize user variable with it.
		
		Calendar calendar=Calendar.getInstance(); //getting the instance of the calendar
		if((tokenClass.getExpirationTime().getTime()-calendar.getTime().getTime())<=0) // checking on the expiration of token 
		{   /// it should be refresh token create new token and send another email with this new token
			verificationTokenRepository.delete(tokenClass);// delete the token if expired 
			return "expired"; //return this message if token expired
		}
		
		user.setEnabled(true); // enable user to login if the token is valid and not expired
		userRepository.save(user); // update the user with the updated attribute enabled = true
		return "valid"; //return valid to use it in the condition of controller method that @GetMapping("/verifyEmail")
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	
	/**
	 * getting current email for user for getting user object including its all data or using of user email for another operations (Auditing)
	 */
	@Override
	public String getCurrentUsername() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            return authentication.getName();
//        }
        return null; // No authenticated user found
    }

	
	
	@Transactional
	@Override
	public void deleteUser(Long id) {
		
		Optional<User>theUser=userRepository.findById(id); // find the user
		theUser.ifPresent(user -> verificationTokenRepository.deleteByUserId(user.getId())); // if the user present then delete the user token from user verification token 
		userRepository.delete(theUser.get());//delete user 
	}

	@Transactional
	@Override
	public User updateUSer(Long id,updateContactsRequest user) {
		
			if(findById(id).isPresent())
			{
				User oldUser=findById(id).get();
				if(!user.getContacts().isEmpty())
				{
					oldUser.setContacts(user.getContacts());
				}
				
				User insertedUser= userRepository.save(oldUser);
				return oldUser;
			}else 
			{
				return null;
			}
		
			
			
			
		}

	
	
	@Override
	public User followUser(Long userId,User user) throws UserException{
		
		Optional<User> userReturned=findById(userId);
		User followToUser=null;
		if(userReturned.isPresent())
		{
			followToUser=userReturned.get();
		}
		if(user.getFollowings().contains(followToUser) 
				&& followToUser.getFollowers().contains(user))
		{
			user.getFollowings().remove(followToUser);
			followToUser.getFollowers().remove(user);
		}else 
		{
			user.getFollowings().add(followToUser);
			followToUser.getFollowers().add(user);
		}
		userRepository.save(followToUser);
		userRepository.save(user);
		return followToUser;		
	}


	@Override
	public User findUserByJwt(String jwt) throws UserException {
		String email =jwtProvider.getEmailFromToken(jwt);
		Optional<User> user=userRepository.findByEmail(email);
		if(!user.isPresent())
		{
			throw new UserException("user not found with email "+email);
		}
		return user.get();
	}



	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

}
