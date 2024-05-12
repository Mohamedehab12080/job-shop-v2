package com.example.JOBSHOP.JOBSHOP.jobSeeker.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceInerface;
import com.example.JOBSHOP.JOBSHOP.Employer.Employer;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.service.postServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Registration.event.registrationCompleteEvent;
import com.example.JOBSHOP.JOBSHOP.Registration.event.listener.registrationCompleteEventListener;
import com.example.JOBSHOP.JOBSHOP.Registration.exception.UserException;
import com.example.JOBSHOP.JOBSHOP.Registration.service.serviceInterfaces.userServiceInterface;
import com.example.JOBSHOP.JOBSHOP.User.model.Role;
import com.example.JOBSHOP.JOBSHOP.User.model.User;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.userProfile;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyAdministrator;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.companyProfile;
import com.example.JOBSHOP.JOBSHOP.companyAdministrator.companyProfile.DTO.companyProfileDTO;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfileService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.DTO.jobSeekerProfileDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.DTO.jobSeekerProfileMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service.jobSeekerQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.requests.saveSkillsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.applicationReturnedSkillsAndQualifications;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.service.jobSeekerServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service.jobSeekerSkillServiceInterface;
import com.example.JOBSHOP.JOBSHOP.response.ApiResponse;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

import jakarta.mail.MessagingException;

import com.example.JOBSHOP.JOBSHOP.Registration.event.listener.registrationCompleteEventListener;

@RestController
@RequestMapping("/api/jobSeekers")
@CrossOrigin(origins = "http://localhost:3000")
public class jobSeekerRestController {

	@Autowired
	private jobSeekerServiceInterface jobSeekerServiceI;
	@Autowired
	private jobSeekerQualificationServiceInterface jobSeekerQualificationServiceI;
	@Autowired
	private jobSeekerSkillServiceInterface jobSeekerSkillServiceI;
	@Autowired
	private applicationServiceInerface applicationServiceI;
	@Autowired
	private jobSeekerProfileService jobSeekerProfileService;
	@Autowired
	private registrationCompleteEventListener registrationCompleteEventListener;
	
	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private postServiceInterface postServiceI;
	@Autowired
	private followService followService;
	
	@Autowired
	private ApplicationEventPublisher applicationPublisher; //application publisher that publish the event for specific class
//	@PostMapping("/save-jobSeeker")
//	public String saveJobSeeker(@ModelAttribute("jobSeeker") jobSeeker jobSeeker,@RequestParam("profileImage") MultipartFile profileImage) throws IOException
//	{
//		
//		return "redirect:/companyAdmin/form";
//	}
//	
//	private jobSeekerQualificationDTO convertJobSeekerQualification(jobSeekerQualification jobSeekerQualification)
//	{
//		return jobSeekerQualificationMapper.mapJobSeekerQualificationToDTO(jobSeekerQualification);
//	}
//	private jobSeekerSkillDTO convertJobSeekerSkill(jobSeekerSkill jobSeekerSkill)
//	{
//		return jobSeekerSkillMapper.mapJobSeekerSkillToDTO(jobSeekerSkill);
//	}
	
		@PostMapping("/save-skills-qualifications")
		@ResponseBody
		public ResponseEntity<ApiResponse> saveJobSeekerQualificationsSkills(
				@RequestBody saveSkillsRequest requestData,
				@RequestHeader("Authorization") String jwt) throws UserException
		{
			User reqUSer=userServiceI.findUserByJwt(jwt);
			if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
			{
				requestData.setUserId(reqUSer.getId());
				String saveSkillResult=jobSeekerServiceI
						.insertJobSeekerSkillsAndQualificationsOptimized(requestData); // instead of List of objects it will be list of skills and list of qualifications 
				if(saveSkillResult.contains("inserted"))
				{
					ApiResponse apiRes=new ApiResponse("jobSeeker Skills and Qualifications Inserted successfully",true);
					return new ResponseEntity<ApiResponse>(apiRes,HttpStatus.CREATED);
			    }else 
				{
					ApiResponse apiRes=new ApiResponse("can't be inserted",false);
					return new ResponseEntity<ApiResponse>(apiRes,HttpStatus.BAD_REQUEST);
				}
			}else 
			{
				throw new UserException("user not found for this token");
			}
			
		}
	
	@DeleteMapping("/delete-skill/{jobSeekerSkillId}") //(Tested)
	public ResponseEntity<ApiResponse> deleteSkill(
			@PathVariable("jobSeekerSkillId") Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			String result=jobSeekerSkillServiceI.deleteById(id);
			if(result.equals("deleted"))
			{
				ApiResponse apiRes=new ApiResponse("Deleted Successfully",true);
				return new ResponseEntity<>(apiRes,HttpStatus.OK);
			}else 
			{
				ApiResponse apiRes=new ApiResponse("Deleted Successfully",false);
				return new ResponseEntity<>(apiRes,HttpStatus.NOT_ACCEPTABLE);
			}
		}else 
		{
			throw new UserException("user not foud for this token");
		}
		
	}
	
	@DeleteMapping("/delete-qual/{jobSeekerQualificationId}") //(Tested)
	public ResponseEntity<ApiResponse> deleteQualification(
			@PathVariable("jobSeekerQualificationId") Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			String result=jobSeekerQualificationServiceI.deleteById(id);
			if(result.equals("deleted"))
			{
				ApiResponse apiRes=new ApiResponse("deleted successfully",true);
				return new ResponseEntity<>(apiRes,HttpStatus.OK);
			}else 
			{
				ApiResponse apiRes=new ApiResponse("can't be deleted",false);
				return new ResponseEntity<>(apiRes,HttpStatus.NOT_ACCEPTABLE);
			}
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}

	@DeleteMapping("/delete-Application/{appId}") //(Tested)
	public ResponseEntity<ApiResponse> deleteApplication(
			@PathVariable("appId") Long id
			,@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
				applicationServiceI.deleteById(id);
				ApiResponse apiRes=new ApiResponse("deleted successfully",true);
				return new ResponseEntity<>(apiRes,HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	@PostMapping("/apply")
	public ResponseEntity<?>Apply(
			@RequestBody applicationDTO app
			,@RequestHeader("Authorization") String jwt) throws UserException, UnsupportedEncodingException, MessagingException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			
			applicationReturnedSkillsAndQualifications applicationReturnedSkillsAndQualifications=jobSeekerServiceI.applyForPost(app);
			
			if(applicationReturnedSkillsAndQualifications.isMatched())
			{
//				postDTO postApplied=postMapper.mapPostTODTO(postServiceI.findById(app.getPostId()));
//				
//				if(postApplied !=null)
//				{
					User sendToUser=new User();
					sendToUser.setEmail(applicationReturnedSkillsAndQualifications.getEmployerEmail());
					sendToUser.setUserName(applicationReturnedSkillsAndQualifications.getEmployerUserName());
					registrationCompleteEventListener
							.sendMailAfterAcceptedApplyToTheCompany(
									sendToUser,
									"http://localhost:3000/postDetails/"+applicationReturnedSkillsAndQualifications.getPostId(),
									""+applicationReturnedSkillsAndQualifications.getApplicationId(),
									applicationReturnedSkillsAndQualifications.getPostTitle());
//				}
			}
			
			return new ResponseEntity<>
			(
					applicationReturnedSkillsAndQualifications
					,HttpStatus.CREATED);	
		
		}else 
		{
			throw new UserException("user not found for this token");
		}
//		System.out.println("Application saved Skills: "+app.getJobSeeker().getSkills()+
//				": application Post Skills: "+app.getPost().getPostFields().get(1).getEmployerField().getCompanyField().getSkills());
	}
	
	
	@GetMapping("/check-apply/{jobSeekerId}/{postId}")
	public ResponseEntity<Boolean> checkForApply (@PathVariable("jobSeekerId") Long jobSeekerId,@PathVariable("postId") Long postId)
	{
		Application app= applicationServiceI.findByJobSeekerIdAndPostId(jobSeekerId, postId);
		if(app!=null)
		{
			return new ResponseEntity<Boolean>(false,HttpStatus.ACCEPTED);
		}else 
		{
			return new ResponseEntity<Boolean>(true,HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/findAllApplications/{id}")
	public ResponseEntity<List<applicationDTO>>
	findAllApplicationsWithJobSeekerID(
			@PathVariable("id") Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().equals(Role.jobSeeker))
		{
			return new ResponseEntity<List<applicationDTO>>(
					jobSeekerServiceI
					.findAllApplicationsForJobSeeker(reqUSer.getId())
					.stream()
					.map(this::convertApplications)
					.collect(Collectors.toList()),HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	 
	private applicationDTO convertApplications(Application app)
	{
		applicationMapper appMapper=new applicationMapper(postServiceI);
		return appMapper.mapApplicationToDTOIncludingPostSkillsAndQualifications(app);
	}
	
	@GetMapping("/findSkillsAndQualificationsForuserItself/{id}")
	private ResponseEntity<?> findSkillsAndQualifications
	(		@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			return ResponseEntity.ok(
					jobSeekerServiceI
					.getJobSeekerSkillsAndQualificaitonsByJobSeekerId(id));
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@GetMapping("/findSkillsAndQualificationsForAnyUserView/{userId}")
	private ResponseEntity<?> findSkillsAndQualificationsForAnyUserView
	(@PathVariable("userId") Long userId,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			return ResponseEntity.ok(
					jobSeekerServiceI
					.getJobSeekerSkillsAndQualificaitonsByJobSeekerId(userId));
		}else 
		{
			throw new UserException("user not found for this token");
		}
	}
	
	@PutMapping("/insertPicture/{id}")
	public ResponseEntity<?> uploadFile(@PathVariable Long id,@RequestBody String picture) throws SQLException, IOException
	{	
		try {
			return ResponseEntity.ok(jobSeekerServiceI.insertPicture(id,picture));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id)
	{
		return ResponseEntity.ok(jobSeekerServiceI.findById(id));
	}
	
	@GetMapping("/findProfileForAnyUserView/{userId}")//(Tested)
	public ResponseEntity<jobSeekerProfileDTO> findCompanyProfile(
			@PathVariable("userId") Long userId,
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			jobSeeker jobSeekerObj=new jobSeeker();
			jobSeekerObj.setId(reqUSer.getId());
			
			userProfile user = new userProfile(jobSeekerObj, followService);
//			System.out.println(""+user.getFollowings().get(0).getUserName());
			Optional<jobSeekerProfile> jobSeekerProfileRetrieved = jobSeekerProfileService.findByJobSeeker_id(reqUSer.getId());
			if(jobSeekerProfileRetrieved.isPresent())
			{
				if(!user.getFollowers().isEmpty())
				{
					jobSeekerProfileRetrieved.get().setFollowers(user.getFollowers());
					
				}else if(!user.getFollowings().isEmpty())
				{
					jobSeekerProfileRetrieved.get().setFollowings(user.getFollowings());
				}else 
				{
					jobSeekerProfileRetrieved.get().setFollowings(user.getFollowings());
					jobSeekerProfileRetrieved.get().setFollowers(user.getFollowers());
				}
			}else 
			{
				throw new UserException("profile not found for this jobSeeker");
			}
			return new ResponseEntity<jobSeekerProfileDTO>(jobSeekerProfileMapper.mapJobSeekerProfileToDTo(jobSeekerProfileRetrieved.get()),HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
	@GetMapping("/findProfileForJobSeekerItSelf")//(Tested)
	public ResponseEntity<jobSeekerProfileDTO> findProfileForJobSeekerItself(
			@RequestHeader("Authorization") String jwt) throws UserException
	{
		User reqUSer=userServiceI.findUserByJwt(jwt);
		if(reqUSer!=null && reqUSer.getUserType().name().equals("jobSeeker"))
		{
			jobSeeker jobSeekerObj=new jobSeeker();
			jobSeekerObj.setId(reqUSer.getId());
			
			userProfile user = new userProfile(jobSeekerObj, followService);
//			System.out.println(""+user.getFollowings().get(0).getUserName());
			Optional<jobSeekerProfile> jobSeekerProfileRetrieved = jobSeekerProfileService.findByJobSeeker_id(reqUSer.getId());
			if(jobSeekerProfileRetrieved.isPresent())
			{
				if(!user.getFollowers().isEmpty())
				{
					jobSeekerProfileRetrieved.get().setFollowers(user.getFollowers());
					
				}else if(!user.getFollowings().isEmpty())
				{
					jobSeekerProfileRetrieved.get().setFollowings(user.getFollowings());
				}else 
				{
					jobSeekerProfileRetrieved.get().setFollowings(user.getFollowings());
					jobSeekerProfileRetrieved.get().setFollowers(user.getFollowers());
				}
			}else 
			{
				throw new UserException("profile not found for this jobSeeker");
			}
			return new ResponseEntity<jobSeekerProfileDTO>(jobSeekerProfileMapper.mapJobSeekerProfileToDTo(jobSeekerProfileRetrieved.get()),HttpStatus.OK);
		}else 
		{
			throw new UserException("user not found for this token");
		}
		
	}
	
//	public jobSeeker mapJobSeekerRequestToJobSeeker(jobSeekerRequest jobSeeker) throws SQLException, IOException
//	{
//		jobSeeker dto=new jobSeeker();
//		dto.setId(jobSeeker.getId());
//		dto.setCreatedBy(jobSeeker.getCreatedBy());
//		dto.setAddress(jobSeeker.getAddress());
//		dto.setContacts(jobSeeker.getContacts());
//		dto.setCreatedDate(jobSeeker.getCreatedDate());
//		dto.setLastModifiedBy(jobSeeker.getLastModifiedBy());
//		dto.setLastModifiedDate(jobSeeker.getLastModifiedDate());
//		dto.setEmail(jobSeeker.getEmail());
//		dto.setPassword(jobSeeker.getPassword());
//		dto.setUserName(jobSeeker.getUserName());
//		dto.setStatuseCode(jobSeeker.getStatuseCode());
//		dto.setUserType(jobSeeker.getUserType());
//		dto.setEducation(jobSeeker.getEducation());
//		dto.setSkills(jobSeeker.getSkills());
//		dto.setPicture(createBlob(jobSeeker.getPicture().getBytes()));
//		dto.setEmploymentState(jobSeeker.getEmploymentState()); 
//		return dto;
//	}
//	@PostMapping("/insertBlob")
//	public ResponseEntity<?> insertJobSeekerBLOB(@RequestBody jobSeeker jobSeeker) throws SQLException, IOException
//	{
//		jobSeeker.setPicture(createBlob((MultipartFile)jobSeeker.getPicture()));
//		return ResponseEntity.ok(jobSeeker);
//	}
//	private Blob createBlob(byte[] data) throws SQLException, IOException {
//        return new SerialBlob(data);
//    } 
	

}
