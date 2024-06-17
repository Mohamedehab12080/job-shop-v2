package com.example.JOBSHOP.JOBSHOP.Registration.controllers;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	
		@RequestMapping("/search")
		@ResponseBody
		public ResponseEntity<?> SearchUsers(@RequestParam String query,
				@RequestHeader("Authorization") String jwt) throws UserException
		{
			User reqUSer=userServiceI.findUserByJwt(jwt);
			if(reqUSer!=null)
			{
			
				System.out.println("The Sent query : "+query);
				List<User> usersReturned=userServiceI.searchUser(query);
				System.out.println("REturned Users List : "+usersReturned);
				List<UserDTO> userdtos=userDTOMapper.toUserDTos(usersReturned);
//				System.out.println("REturned User : "+userdtos.get(0).getUserName());

				return new ResponseEntity<>(userdtos,HttpStatus.OK);

//				if(userdtos!=null && !userdtos.isEmpty())
//				{
//					return new ResponseEntity<>(usersReturned,HttpStatus.ACCEPTED);
//				}else
//				{
//					return new ResponseEntity<>(Collections.emptyList(),HttpStatus.ACCEPTED);
//				}
			}else 
			{
				throw new UserException("user not found for this token");
			}
		}
	
	
	@PutMapping("/updateContacts")
	public ResponseEntity<?> updateContactsForUser(
			@RequestBody updateContactsRequest updateContactsRequest
			,@RequestHeader("Authorization")String jwt) throws UserException
	{
		User user =userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			if(userServiceI.updateUSer(user.getId(), updateContactsRequest)!=null)
			{
				return new ResponseEntity<>(userServiceI.updateUSer(user.getId(), updateContactsRequest),HttpStatus.OK);
			}else 
			{
				return new ResponseEntity<>("Not FOund",HttpStatus.NOT_FOUND);
			}
			
		}else
		{
			throw new UserException("user notfound for this token");
		}
	}
	
	@PutMapping("/follow/{userId}")
	public ResponseEntity<UserDTO> followUnfollow(@PathVariable Long userId,
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
	
	
	
}
