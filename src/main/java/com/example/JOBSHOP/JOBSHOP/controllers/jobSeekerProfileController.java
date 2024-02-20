package com.example.JOBSHOP.JOBSHOP.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.services.followService;
import com.example.JOBSHOP.JOBSHOP.services.jobSeekerService;

@RestController
@RequestMapping("/jobSeekerProfile")
public class jobSeekerProfileController {


	@Autowired
	private jobSeekerService jobSeekerService;
	@Autowired
	private followService followService;
	
	@GetMapping("/getInfo/{jobSeekerId}")
	public ResponseEntity<?> getAllJobSeekerDataWithId(@PathVariable Long jobSeekerId)
	{
		jobSeekerProfile profile=new jobSeekerProfile(jobSeekerService.getReferenceById(jobSeekerId),followService);
		System.out.println("Jobseeker Followers:"+profile.getFollowers()+"\n Followings: "+profile.getFollowings());
		return ResponseEntity.ok(profile);
	}
	
}
