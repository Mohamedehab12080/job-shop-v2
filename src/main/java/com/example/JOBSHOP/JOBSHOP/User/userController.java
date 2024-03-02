package com.example.JOBSHOP.JOBSHOP.User;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JOBSHOP.JOBSHOP.Follow.followRequest;
import com.example.JOBSHOP.JOBSHOP.Follow.followService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeekerService;

@RestController
@RequestMapping("/user")
public class userController {

	@Autowired
	private userService userService;
	@Autowired
	private companyAdminService companyService;
	@Autowired
	private jobSeekerService jobSeekerService;

	@Autowired
	private followService followService;
	
	@PostMapping("/createFollow")
	public ResponseEntity<?> createFollow(@RequestBody followRequest params)
	{
		return ResponseEntity.ok(followService.createFollow(params.getFollower().getId(), params.getFollwing().getId()));
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
}
