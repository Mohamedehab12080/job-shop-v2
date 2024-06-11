package com.example.JOBSHOP.JOBSHOP.Employer.employerProfile;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.DTO.employerDTOMapper;
import com.example.JOBSHOP.JOBSHOP.Employer.service.employerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userUtils;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;

@RestController
@RequestMapping("/api/employerProfile")
public class employerProfileController {

	@Autowired
	private employerServiceInterface employerServiceI;
	@Autowired
	private followService followService;
	
	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private postServiceInterface postServiceI;
	
	@GetMapping("/getInfo/{empId}")
	public ResponseEntity<employerProfileResponse> getAllEmployerProfileInfo(
				@PathVariable Long empId,
				@RequestHeader("Authorization") String jwt
			)throws UserException
	{
		
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			boolean isrequestUser=false;
			Employer employer=employerServiceI.findById(empId);
			employerProfile profile=new employerProfile(employer,followService);
			if(user.getId() == empId){
				isrequestUser=true;
			}else 
			{
				isrequestUser=false;
			}
			
			employerDTO employerDTO=employerDTOMapper.mapEmployerToDTO(employer);
			employerDTO.setReq_user(userUtils.isReqUser(user,employer));
			employerDTO.setFollowed(userUtils.isFollowedByReqUser(user, employer));
			
			employerProfileResponse response=new employerProfileResponse( 
					profile,isrequestUser,
					employerDTO,
					postServiceI
					.findByEmployerId(empId)
					.stream()
					.map(postMapper::mapPostTODTO)
					.collect(Collectors.toList())); 
			return new ResponseEntity<employerProfileResponse>(response,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
	
	}
}
