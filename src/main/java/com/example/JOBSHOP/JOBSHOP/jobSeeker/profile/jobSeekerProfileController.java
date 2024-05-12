package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.DTO.UserDTO;
import com.example.JOBSHOP.JOBSHOP.User.DTO.userDTOMapper;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.service.userUtils;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.DTO.jobSeekerMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.controller.skillsAndQualificationsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerServiceInterface;
@RestController
@RequestMapping("/api/jobSeekerProfile")
public class jobSeekerProfileController {


	@Autowired
	private jobSeekerServiceInterface jobSeekerService;
	@Autowired
	private followService followService;
	
	@Autowired
	private userServiceInterface userServiceI;
	
	@GetMapping("/getInfo/{jobSeekerId}")
//	@PreAuthorize("hasRole('jobSeeker')")
	public ResponseEntity<profileResponse> getAllJobSeekerDataWithId(
			@PathVariable Long jobSeekerId
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User user=userServiceI.findUserByJwt(jwt);
		if(user!=null)
		{
			boolean isrequestUser=false;
			jobSeeker jobSeekerFetched=jobSeekerService.findById(jobSeekerId);
			jobSeekerProfile profile=new jobSeekerProfile(jobSeekerFetched,followService);
			System.out.println("Jobseeker Followers:"+profile.getFollowers()+"\n Followings: "+profile.getFollowings());
			if(user.getId() == jobSeekerId){
				isrequestUser=true;
			}else 
			{
				isrequestUser=false;
			}
			jobSeekerDTO dtoJobSeeker=jobSeekerMapper.mapJobSeekerToDTO(jobSeekerFetched);
			
			dtoJobSeeker.setReq_user(userUtils.isReqUser(user,jobSeekerFetched));
			dtoJobSeeker.setFollowed(userUtils.isFollowedByReqUser(user, jobSeekerFetched));
//			skillsAndQualificationsRequest skillsAndQualificationsRequest=jobSeekerService.getJobSeekerSkillsAndQualificaitonsByJobSeekerId(jobSeekerId);
			profileResponse response=new profileResponse(profile, isrequestUser,dtoJobSeeker);
			return new ResponseEntity<profileResponse>(response,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
}
