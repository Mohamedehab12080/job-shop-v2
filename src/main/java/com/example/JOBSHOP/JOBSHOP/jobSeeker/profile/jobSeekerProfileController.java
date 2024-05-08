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
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;
@RestController
@RequestMapping("/api/jobSeekerProfile")
public class jobSeekerProfileController {


	@Autowired
	private jobSeekerService jobSeekerService;
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
		if(user!=null && user.getUserType().equals(Role.jobSeeker))
		{
			boolean isrequestUser=false;
			jobSeekerProfile profile=new jobSeekerProfile(jobSeekerService.findById(jobSeekerId),followService);
			System.out.println("Jobseeker Followers:"+profile.getFollowers()+"\n Followings: "+profile.getFollowings());
			if(user.getId() == jobSeekerId){
				isrequestUser=true;
			}else 
			{
				isrequestUser=false;
			}
			
			profileResponse response=new profileResponse(profile, isrequestUser);
			return new ResponseEntity<profileResponse>(response,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
}
