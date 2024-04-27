package com.example.JOBSHOP.JOBSHOP.Registration.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.userService;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.DTO.userDTOMapper;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userUtils;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorDTO;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.DTO.companyAdministratorMapper;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdministratorServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerServiceInterface;

@Controller
@RequestMapping("/api/user")
public class userController {
	
	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private jobSeekerServiceInterface jobSeekerServiceI;
	
	@Autowired
	private companyAdministratorServiceInterface companyAdminServiceI;
	
	@GetMapping("/find/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null)
		{
			Optional<User> user=userServiceI.findById(id);
			if(user.isPresent())
			{
				UserDTO userdto=userDTOMapper.mapDTOTOUserForFollow(user.get());
				userdto.setReq_user(userUtils.isReqUser(reqUSer, user.get()));
				userdto.setFollowed(userUtils.isFollowedByReqUser(reqUSer, user.get()));
				return new ResponseEntity<UserDTO>(userdto,HttpStatus.OK);
			}else 
			{
				throw new UserException("user not found for this id");
			}
		}else
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@GetMapping("/findByJwt")
	public ResponseEntity<UserDTO> getUserAfterLogin(
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null)
		{
		
				UserDTO userdto=userDTOMapper.mapDTOTOUserForFollow(reqUSer);
				return new ResponseEntity<UserDTO>(userdto,HttpStatus.OK);
		}else
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserDTO>> SearchUsers(@RequestParam String query,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null)
		{
			List<User> usersReturned=userServiceI.searchUser(query);
			
			if(!usersReturned.isEmpty())
			{
				List<UserDTO> userdtos=userDTOMapper.toUserDTos(usersReturned);
				
				return new ResponseEntity<>(userdtos,HttpStatus.ACCEPTED);
			}else 
			{
				throw new UserException("user not found for this search");
			}
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@PutMapping("/follow/{userId}")
	public ResponseEntity<UserDTO> SearchUsers(@PathVariable Long userId,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null)
		{
			User usersReturned=userServiceI.followUser(userId,reqUSer);
			
			if(usersReturned!=null)
			{
				UserDTO userdto=userDTOMapper.mapDTOTOUserForFollow(usersReturned);
				userdto.setFollowed(userUtils.isFollowedByReqUser(reqUSer, usersReturned));
				return new ResponseEntity<>(userdto,HttpStatus.ACCEPTED);
			}else 
			{
				throw new UserException("user not found for this search");
			}
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	@PutMapping("/jobSeeker/update")
	public ResponseEntity<jobSeekerDTO> updateJobSeekerUser(@RequestBody jobSeeker req,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null)
		{
			jobSeeker returnedUser=jobSeekerServiceI.update(reqUSer.getId(), req);
			
			if(returnedUser!=null)
			{
				jobSeekerDTO userDto=jobSeekerMapper.mapJobSeekerToDTO(returnedUser); // i will map like this for jobSeeker
				
				return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
			}else 
			{
				throw new UserException("User can't be updated");
			}
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	
	@PutMapping("/company/update")
	public ResponseEntity<companyAdministratorDTO> updateCompanyUser(@RequestBody companyAdministrator req,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("Admin"))
		{
			companyAdministrator returnedUser=companyAdminServiceI.update(reqUSer.getId(),req);
			
			if(returnedUser!=null)
			{
				companyAdministratorDTO userDto=
						companyAdministratorMapper
						.mapCompanyAdminToDTO(returnedUser); // i will map like this for company Admin
				
				return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
			}
			
			else 
			{
				throw new UserException("User can't be updated");
			}
			
		}
		
		else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
}
