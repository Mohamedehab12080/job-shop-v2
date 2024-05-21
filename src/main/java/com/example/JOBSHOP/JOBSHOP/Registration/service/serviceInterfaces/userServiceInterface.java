package com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces;

import java.util.List;
import java.util.Optional;

import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.Registration.controllers.updateContactsRequest;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.tokens.verificationToken;
import com.example.JOBSHOP.JOBSHOP.User.model.User;

public interface userServiceInterface {

	
	User updateUSer(Long id ,updateContactsRequest updateContactsRequest);
	User findUserByJwt(String jwt) throws UserException;
	List<User> searchUser(String query);
	void deleteUser(Long id);
	String getCurrentUsername();
	verificationToken findVerificationToken(String verificationToken);
	List<User> getAllUsers();
	companyAdministrator registerCompanyAdministrator(companyAdministratorDTO registrationRequest);
	jobSeeker registerJobSeeker(jobSeekerDTO registrationRequest);
	
	Optional<User> findByEmail(String email);

	void saveUserVerificationToken(User user,String verificationToken);
	String validateToken(String theToken);
	Optional<User> findById(Long id);
	User followUser(Long userId, User user) throws UserException;  
}
