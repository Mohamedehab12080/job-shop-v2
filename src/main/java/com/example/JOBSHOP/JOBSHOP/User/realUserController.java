package com.example.JOBSHOP.JOBSHOP.User;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Registration.password.iPasswordResetTokenService;
import com.example.JOBSHOP.JOBSHOP.Registration.password.passwordResetToken;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.DTO.userDTOMapper;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.realUserService;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.DTO.followDTOMapper;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followRequest;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.service.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class realUserController {

	@Autowired
	private realUserService userService;
	@Autowired
	private companyAdminService companyService;
	@Autowired
	private jobSeekerService jobSeekerService;

	@Autowired
	private followService followService;
	
	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private iPasswordResetTokenService iPasswordResetTokenService;
	
	
	@PostMapping("/createFollow")
	public ResponseEntity<?> createFollow(@RequestBody followRequest params)
	{
		return ResponseEntity.ok(followDTOMapper.mapFollowToDTO(followService
				.createFollow(
						params.getFollower()
						.getId(), 
						params.getFollwing().getId())));
	}
	
	private UserDTO convertUserTODTO(User user)
	{
		return userDTOMapper.mapDTOTOUserForFollow(user);
	}
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllFollow()
	{
		return ResponseEntity.ok(followService.findAll());
	}
	
	@GetMapping("/getFollowersName/{follwingId}")
	public ResponseEntity<?> getFollowersName(@PathVariable Long follwingId)
	{
		User user=new User();
		user.setId(follwingId);
		return ResponseEntity.ok(followService.getFollowersUserNameById(user));
	}
	@GetMapping("/getFollowersNames/{userName}")
	public ResponseEntity<?> getFollowersNameByName(@PathVariable String userName)
	{
		User user=new User();
		user.setUserName(userName);
		return ResponseEntity.ok(followService.getFollowersUserNameById(user));
	}
	@GetMapping("/getFollowers/{follwingId}")
	public ResponseEntity<?> getFollowers(@PathVariable Long follwingId)
	{
		User user=new User();
		user.setId(follwingId);
		return ResponseEntity.ok(followService.getFollowersById(user));
	}
	
	@GetMapping("/getFollowings/{followerId}")
	public ResponseEntity<?> getFollowings(@PathVariable Long followerId)
	{
		User user=new User();
		user.setId(followerId);
		return ResponseEntity.ok(followService.getFollowingById(user));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert (@RequestBody User user) {
		return ResponseEntity.ok(userService.insert(user));
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll (){
		return ResponseEntity.ok(userService.findAll());
	}
	@GetMapping("/all/company")
	public ResponseEntity<?> findAllCompany(){
		return ResponseEntity.ok(companyService.findAll());
	}
	@GetMapping("/all/jobSeeker")
	public ResponseEntity<?> findAllJobSeeker(){
		return ResponseEntity.ok(jobSeekerService.findAll());
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<?> resetPasswordRequest(HttpServletRequest request,@RequestParam("email") String email)
	{
		passwordResetToken alreadyToken;
		Optional<User> user =userServiceI.findByEmail(email);
		if(user.isPresent())
		{
			Optional<passwordResetToken> alreadyTokenOptional=iPasswordResetTokenService.findPasswordTokenByUser(user.get());
			if(alreadyTokenOptional.isPresent())
			{
				alreadyToken=alreadyTokenOptional.get();
				return ResponseEntity.ok("Already Verified");
			}else
			{
//				String ResetToken=UUID.randomUUID().toString();//generate random token
//				iPasswordResetTokenService.saveResetPasswordVerificationToken(ResetToken, user.get()); //save the token generated for the user found
//				String url =applicationUrl(request)+"/register/password-reset-form?token="+ResetToken; //making the url including the created token at the parameter token
//				try {
//					registrationCompleteEventListener.sendVerificationEmailPasswordReset(user,url); //sending the email from the listener including the created url
//				} catch (UnsupportedEncodingException | MessagingException e) {
//					model.addAttribute("error",e.getMessage());//display the error if occured
//				}
				
			return null;
			}
		}else 
		{
			return null;
		}
	}
}
