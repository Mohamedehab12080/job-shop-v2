package com.example.JOBSHOP.JOBSHOP.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.JOBSHOP.JOBSHOP.models.Employer;
import com.example.JOBSHOP.JOBSHOP.models.Post;
import com.example.JOBSHOP.JOBSHOP.models.User;
import com.example.JOBSHOP.JOBSHOP.models.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.models.companyProfile;
import com.example.JOBSHOP.JOBSHOP.models.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.models.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.services.companyAdminService;
import com.example.JOBSHOP.JOBSHOP.services.employerService;
import com.example.JOBSHOP.JOBSHOP.services.followService;
import com.example.JOBSHOP.JOBSHOP.services.jobSeekerService;
import com.example.JOBSHOP.JOBSHOP.services.postService;
import com.example.JOBSHOP.JOBSHOP.services.userService;

@Component
public class startUp implements CommandLineRunner{

	
//	@Autowired
//	private userService userService;
//	
//	@Autowired
//	private jobSeekerService jobSeekerService;
//	
//	@Autowired 
//	private employerService employerService;
//	
//	@Autowired
//	private companyAdminService companyAdminService;
//	@Autowired
//	private followService followService;
//	
//	@Autowired
//	private postService postService;
	@Override
	public void run(String... args) throws Exception {
		
//		List<String> contacts= new ArrayList<String>();
//		contacts.add("01091499680");
// 		contacts.add("mohamed_ehab61");
// 		if(userService.findAll().isEmpty())
// 		{
// 			for (int i = 1; i<50; i++) {
// 				jobSeeker user=new jobSeeker();
// 				user.setUserName("Mohamed"+i);
// 				user.setAddress("Address"+i);
// 				user.setEmail("Mohamedw@gmail.coms"+i);
// 				user.setPassword("12345"+i);
// 				user.setContacts(contacts);
// 				user.setUserType("job_Seeker");
// 				user.setExperience("5 Years");
// 				jobSeekerService.insert(user);
// 			
// 			}
// 			for (int i = 1; i<50; i++) {
// 			companyAdministrator companyAdmin=new companyAdministrator();
//				companyAdmin.setUserName("Mohamed"+i);
//				companyAdmin.setAddress("Address"+i);
//				companyAdmin.setEmail("Mohamedsss@gmail.coms"+(i+1));
//				companyAdmin.setPassword("12345"+i);
//				companyAdmin.setContacts(contacts);
//				companyAdmin.setUserType("company");
//				companyAdmin.setCompanyName("MO_Software"+i);
//				companyAdminService.insert(companyAdmin);
// 			}
// 			companyAdministrator companyD=new companyAdministrator();
// 			companyD.setId(Long.valueOf(50));
// 			for (int i = 1; i<50; i++) {
// 			Employer employer=new Employer();
//				employer.setUserName("Mohamed"+i);
//				employer.setAddress("Address"+i);
//				employer.setEmail("Mohameds@gmail.coms"+(i+2));
//				employer.setPassword("12345"+i);
//				employer.setContacts(contacts);
//				employer.setCompanyAdmin(companyD);
//				employer.setUserType("Employer");
//				companyAdminService.createEmployer(employer);
// 			}
// 			if(followService.findAll().isEmpty())
// 			{
// 				for(int i=1;i<20;i++)
// 	 			{
// 	 				followService.createFollow(Long.valueOf(i),Long.valueOf(i+1));
// 	 			}
// 			}
//// 			if(jobSeekerService.findAllProfiles().isEmpty())
//// 			{
//// 				List<jobSeeker> list=jobSeekerService.findAll();
//// 	 			Iterator<jobSeeker> itr=list.iterator();
//// 	 			while(itr.hasNext())
//// 	 			{
//// 	 				jobSeeker seeker=itr.next();
//// 	 				jobSeekerProfile jobSeekerProfile=new jobSeekerProfile(jobSeekerService.getJobSeekersByReference(seeker.getId()),followService);
//// 	 				jobSeekerProfile.setCreatedBy("Mohamed Ehab");
//// 	 				jobSeekerProfile.setCreatedDate(LocalDateTime.now());
//// 	 				jobSeekerService.insertProfile(jobSeekerProfile);
//// 	 			}
//// 	 			
//// 			}
// 			List<companyAdministrator> companyList=companyAdminService.findAll();
//	 			Iterator<companyAdministrator> itr2=companyList.iterator();
//	 			
//	 			
//	 				if(postService.findAll().isEmpty())
//	 		  		{
//	 		  			Post post=new Post();
//	 		  			companyProfile comp=new companyProfile(companyAdminService.findById(Long.valueOf(50)),followService);
//	 		  			comp.setId(Long.valueOf(2));
//	 		  	 		post.setCompanyProfile(comp);
//	 		  	 		post.setCreatedBy("Mohamed Ehab 2");
//	 		  	 		post.setCreatedDate(LocalDateTime.now()); 
//	 		  	 		post.setEmployer(employerService.findById(Long.valueOf(99)));
//	 		  	 		post.setDescription("This post is a test post for test the api");
//	 		  	 		post.setEmploymentType("Full Time");
//	 		  	 		post.setStatusCode("Opened");
//	 		  	 		postService.insert(post);
//	 		  		} 
// 		}			

	}
	}
