package com.example.JOBSHOP.JOBSHOP.jobSeeker.profile;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/jobSeekerProfile")
public class jobSeekerProfileController {


	@Autowired
	private jobSeekerService jobSeekerService;
	@Autowired
	private followService followService;
	
	@GetMapping("/getInfo/{jobSeekerId}")
//	@PreAuthorize("hasRole('jobSeeker')")
	public ResponseEntity<?> getAllJobSeekerDataWithId(@PathVariable Long jobSeekerId)
	{
		jobSeekerProfile profile=new jobSeekerProfile(jobSeekerService.findById(jobSeekerId),followService);
		System.out.println("Jobseeker Followers:"+profile.getFollowers()+"\n Followings: "+profile.getFollowings());
		return ResponseEntity.ok(profile);
	}
	
}
